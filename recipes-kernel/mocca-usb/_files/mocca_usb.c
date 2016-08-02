/*
 * USB Skeleton driver - 2.2
 *
 * Copyright (C) 2001-2004 Greg Kroah-Hartman (greg@kroah.com)
 *
 *	This program is free software; you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as
 *	published by the Free Software Foundation, version 2.
 *
 * This driver is based on the 2.6.3 version of drivers/usb/usb-skeleton.c 
 * but has been rewritten to be easier to read and use.
 *
 */

#include <linux/kernel.h>
#include <linux/errno.h>
#include <linux/init.h>
#include <linux/slab.h>
#include <linux/module.h>
#include <linux/kref.h>
#include <linux/uaccess.h>
#include <linux/usb.h>
#include <linux/mutex.h>
#include <linux/spinlock.h>


/* Define these values to match your devices */
#define USB_MOCCA_VENDOR_ID	0x184f
#define USB_MOCCA_PRODUCT_ID_1	0x0007
#define USB_MOCCA_PRODUCT_ID_2	0x0008
#define USB_MOCCA_PRODUCT_ID_3	0x0009
#define USB_MOCCA_PRODUCT_ID_4	0x0010
#define USB_MOCCA_PRODUCT_ID_5	0x0011
#define USB_MOCCA_PRODUCT_ID_6	0x0012

/* table of devices that work with this driver */
static const struct usb_device_id mocca_table[] = {
	{ USB_DEVICE(USB_MOCCA_VENDOR_ID, USB_MOCCA_PRODUCT_ID_1) },
	{ USB_DEVICE(USB_MOCCA_VENDOR_ID, USB_MOCCA_PRODUCT_ID_2) },
	{ USB_DEVICE(USB_MOCCA_VENDOR_ID, USB_MOCCA_PRODUCT_ID_3) },
	{ USB_DEVICE(USB_MOCCA_VENDOR_ID, USB_MOCCA_PRODUCT_ID_4) },
	{ USB_DEVICE(USB_MOCCA_VENDOR_ID, USB_MOCCA_PRODUCT_ID_5) },
	{ USB_DEVICE(USB_MOCCA_VENDOR_ID, USB_MOCCA_PRODUCT_ID_6) },
	{ }					/* Terminating entry */
};
MODULE_DEVICE_TABLE (usb, mocca_table);


/* Get a minor range for your devices from the usb maintainer */
#define USB_MOCCA_MINOR_BASE	221 /*TODO: change this minor number */

/* our private defines. if this grows any larger, use your own .h file */
#define MAX_TRANSFER		( PAGE_SIZE - 512 )
#define WRITES_IN_FLIGHT	8

/* Structure to hold all of our device specific stuff */
struct mocca_skel {
	struct usb_device *	udev;			/* the usb device for this device */
	struct usb_interface *	interface;		/* the interface for this device */
	struct semaphore	limit_sem;		/* limiting the number of writes in progress */
	unsigned char *		bulk_in_buffer;		/* the buffer to receive data */
	size_t			bulk_in_size;		/* the size of the receive buffer */
	__u8			bulk_in_endpointAddr;	/* the address of the bulk in endpoint */
	__u8			bulk_out_endpointAddr;	/* the address of the bulk out endpoint */
	struct kref		kref;
};
#define to_mocca_dev(d) container_of(d, struct mocca_skel, kref)

static struct usb_driver mocca_driver;
static spinlock_t spinLock;

static void mocca_delete(struct kref *kref)
{	
	struct mocca_skel *dev = to_mocca_dev(kref);

	usb_put_dev(dev->udev);
	kfree (dev->bulk_in_buffer);
	kfree (dev);
}

static int mocca_open(struct inode *inode, struct file *file)
{
	struct mocca_skel *dev;
	struct usb_interface *interface;
	int subminor;
	int retval = 0;

	subminor = iminor(inode);

	interface = usb_find_interface(&mocca_driver, subminor);
	if (!interface) {
		printk(KERN_ERR "%s - error, can't find device for minor %d",
		     __FUNCTION__, subminor);
		retval = -ENODEV;
		goto exit;
	}

	dev = usb_get_intfdata(interface);
	if (!dev) {
		retval = -ENODEV;
		goto exit;
	}

	/* increment our usage count for the device */
	kref_get(&dev->kref);

	/* save our object in the file's private structure */
	file->private_data = dev;

exit:
	return retval;
}

static int mocca_release(struct inode *inode, struct file *file)
{
	struct mocca_skel *dev;

	dev = (struct mocca_skel *)file->private_data;
	if (dev == NULL)
		return -ENODEV;

	/* decrement the count on our device */
	kref_put(&dev->kref, mocca_delete);
	return 0;
}

static ssize_t mocca_read(struct file *file, char *buffer, size_t count, loff_t *ppos)
{
	struct mocca_skel *dev;
	int retval = 0;
	int bytes_read;

	dev = (struct mocca_skel *)file->private_data;
	
	/* do a blocking bulk read to get data from the device */
	retval = usb_bulk_msg(dev->udev,
			      usb_rcvbulkpipe(dev->udev, dev->bulk_in_endpointAddr),
			      dev->bulk_in_buffer,
			      min(dev->bulk_in_size, count),
			      &bytes_read, 10000);

	/* if the read was successful, copy the data to userspace */
	if (!retval) {
		if (copy_to_user(buffer, dev->bulk_in_buffer, bytes_read))
			retval = -EFAULT;
		else
			retval = bytes_read;
	}

	return retval;
}

static void mocca_write_bulk_callback(struct urb *urb)
{
	struct mocca_skel *dev;

	dev = (struct mocca_skel *)urb->context;

	/* sync/async unlink faults aren't errors */
	if (urb->status && 
	    !(urb->status == -ENOENT || 
	      urb->status == -ECONNRESET ||
	      urb->status == -ESHUTDOWN)) {
		printk(KERN_ERR "%s - nonzero write bulk status received: %d",
		    __FUNCTION__, urb->status);
	}

	/* free up our allocated buffer */
	usb_free_coherent(urb->dev, urb->transfer_buffer_length, 
			urb->transfer_buffer, urb->transfer_dma);
	up(&dev->limit_sem);
}

static ssize_t mocca_write(struct file *file, const char *user_buffer, size_t count, loff_t *ppos)
{
	struct mocca_skel *dev;
	int retval = 0;
	struct urb *urb = NULL;
	char *buf = NULL;
	size_t writesize = min(count, (size_t)MAX_TRANSFER);

	dev = (struct mocca_skel *)file->private_data;

	/* verify that we actually have some data to write */
	if (count == 0)
		goto exit;

	/* limit the number of URBs in flight to stop a user from using up all RAM */
	if (down_interruptible(&dev->limit_sem)) {
		retval = -ERESTARTSYS;
		goto exit;
	}

	/* create a urb, and a buffer for it, and copy the data to the urb */
	urb = usb_alloc_urb(0, GFP_KERNEL);
	if (!urb) {
		retval = -ENOMEM;
		goto error;
	}

	buf = usb_alloc_coherent(dev->udev, writesize, GFP_KERNEL, &urb->transfer_dma);
	if (!buf) {
		retval = -ENOMEM;
		goto error;
	}

	if (copy_from_user(buf, user_buffer, writesize)) {
		retval = -EFAULT;
		goto error;
	}

	/* initialize the urb properly */
	usb_fill_bulk_urb(urb, dev->udev,
			  usb_sndbulkpipe(dev->udev, dev->bulk_out_endpointAddr),
			  buf, writesize, mocca_write_bulk_callback, dev);
	urb->transfer_flags |= URB_NO_TRANSFER_DMA_MAP;

	/* send the data out the bulk port */
	retval = usb_submit_urb(urb, GFP_KERNEL);
	if (retval) {
		printk(KERN_ERR "%s - failed submitting write urb, error %d", __FUNCTION__, retval);
		goto error;
	}

	/* release our reference to this urb, the USB core will eventually free it entirely */
	usb_free_urb(urb);

exit:
	return writesize;

error:
	usb_free_coherent(dev->udev, writesize, buf, urb->transfer_dma);
	usb_free_urb(urb);
	up(&dev->limit_sem);
	return retval;
}

static struct file_operations mocca_fops = {
	.owner =	THIS_MODULE,
	.read =		mocca_read,
	.write =	mocca_write,
	.open =		mocca_open,
	.release =	mocca_release,
};

/* 
 * usb class driver info in order to get a minor number from the usb core,
 * and to have the device registered with the driver core
 */
static struct usb_class_driver mocca_class = {
	.name =		"mocca%d",
	.fops =		&mocca_fops,
	.minor_base =	USB_MOCCA_MINOR_BASE,
};

static int mocca_probe(struct usb_interface *interface, const struct usb_device_id *id)
{
	struct mocca_skel *dev = NULL;
	struct usb_host_interface *iface_desc;
	struct usb_endpoint_descriptor *endpoint;
	size_t buffer_size;
	int i;
	int retval = -ENOMEM;

	spin_lock_init(&spinLock);
	
	/* allocate memory for our device state and initialize it */
	dev = kzalloc(sizeof(*dev), GFP_KERNEL);
	if (dev == NULL) {
		printk(KERN_ERR "Out of memory");
		goto error;
	}
	kref_init(&dev->kref);
	sema_init(&dev->limit_sem, WRITES_IN_FLIGHT);

	dev->udev = usb_get_dev(interface_to_usbdev(interface));
	dev->interface = interface;

	/* set up the endpoint information */
	/* use only the first bulk-in and bulk-out endpoints */
	iface_desc = interface->cur_altsetting;
	for (i = 0; i < iface_desc->desc.bNumEndpoints; ++i) {
		endpoint = &iface_desc->endpoint[i].desc;

		if (!dev->bulk_in_endpointAddr &&
		    ((endpoint->bEndpointAddress & USB_ENDPOINT_DIR_MASK)
					== USB_DIR_IN) &&
		    ((endpoint->bmAttributes & USB_ENDPOINT_XFERTYPE_MASK)
					== USB_ENDPOINT_XFER_BULK)) {
			/* we found a bulk in endpoint */
			buffer_size = le16_to_cpu(endpoint->wMaxPacketSize);
			dev->bulk_in_size = buffer_size;
			dev->bulk_in_endpointAddr = endpoint->bEndpointAddress;
			dev->bulk_in_buffer = kmalloc(buffer_size, GFP_KERNEL);
			if (!dev->bulk_in_buffer) {
				printk(KERN_ERR "Could not allocate bulk_in_buffer");
				goto error;
			}
			printk(KERN_INFO "Found bulk-in pipe, adress: %x, max packet size: %d", endpoint->bEndpointAddress, endpoint->wMaxPacketSize);
		}

		if (!dev->bulk_out_endpointAddr &&
		    ((endpoint->bEndpointAddress & USB_ENDPOINT_DIR_MASK)
					== USB_DIR_OUT) &&
		    ((endpoint->bmAttributes & USB_ENDPOINT_XFERTYPE_MASK)
					== USB_ENDPOINT_XFER_BULK)) {
			/* we found a bulk out endpoint */
			dev->bulk_out_endpointAddr = endpoint->bEndpointAddress;
		    printk(KERN_INFO "Found bulk-out pipe, adress: %x, max packet size: %d", endpoint->bEndpointAddress, endpoint->wMaxPacketSize);
		}
	}
	if (!(dev->bulk_in_endpointAddr && dev->bulk_out_endpointAddr)) {
		printk(KERN_ERR "Could not find both bulk-in and bulk-out endpoints");
		goto error;
	}

	/* save our data pointer in this interface device */
	usb_set_intfdata(interface, dev);

	/* we can register the device now, as it is ready */
	retval = usb_register_dev(interface, &mocca_class);
	if (retval) {
		/* something prevented us from registering this driver */
		printk(KERN_ERR "Not able to get a minor for this device.");
		usb_set_intfdata(interface, NULL);
		goto error;
	}

	/* let the user know what node this device is now attached to */
	dev_info(&interface->dev,
		 "K2L MOCCA device now attached to mocca%d",
		 interface->minor);
	return 0;

error:
	if (dev)
		kref_put(&dev->kref, mocca_delete);
	return retval;
}

static void mocca_disconnect(struct usb_interface *interface)
{
	struct mocca_skel *dev;
	int minor = interface->minor;

	/* prevent mocca_open() from racing mocca_disconnect() */
	spin_lock(&spinLock);

	dev = usb_get_intfdata(interface);
	usb_set_intfdata(interface, NULL);

	/* give back our minor */
	usb_deregister_dev(interface, &mocca_class);

	spin_unlock(&spinLock);

	/* decrement our usage count */
	kref_put(&dev->kref, mocca_delete);

	dev_info(&interface->dev, "K2L MOCCA device #%d now disconnected", minor);
}

static struct usb_driver mocca_driver = {
	.name =		"k2l-mocca",
	.probe =	mocca_probe,
	.disconnect =	mocca_disconnect,
	.id_table =	mocca_table,
};

static int __init usb_mocca_init(void)
{
	int result;

	/* register this driver with the USB subsystem */
	result = usb_register(&mocca_driver);
	if (result)
		printk(KERN_ERR "usb_register failed. Error number %d", result);

	return result;
}

static void __exit usb_mocca_exit(void)
{
	/* deregister this driver with the USB subsystem */
	usb_deregister(&mocca_driver);
}

module_init (usb_mocca_init);
module_exit (usb_mocca_exit);

MODULE_LICENSE("GPL");
