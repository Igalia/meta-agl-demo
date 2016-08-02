# Makefile
#

SRC := $(shell pwd)

obj-m += mocca_usb.o
CFLAGS_hdm_usb.o := -I$(src)/../../aim-network/files/ -I$(src)/../../mostcore/files 

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) clean

