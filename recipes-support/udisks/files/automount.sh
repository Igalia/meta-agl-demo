#!/bin/sh

pathtoname() {
	udevadm info -p /sys/"$1" | awk -v FS== '/DEVNAME/ {print $2}'
}

MOUNT_OPTIONS="ro,flush"

rmdir /media/* &> /dev/null || true
for DEVNAME in $(udisks --enumerate-device-files|grep -e sd[a-z][0-9]); do
	udisks --mount-options $MOUNT_OPTIONS --mount $DEVNAME
done

stdbuf -oL -- udevadm monitor --udev -s block | while read -r -- _ _ event devpath _; do
	if [ "$event" = add ]; then
		DEVNAME=$(pathtoname "$devpath")
		udisks --mount-options $MOUNT_OPTIONS --mount $DEVNAME
	fi
done
