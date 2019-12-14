#!/bin/bash

# ds1307
echo "ds1307 0x68" > /sys/class/i2c-dev/$1/device/new_device

#ds3231
#echo "ds1307 0x57" > /sys/class/i2c-dev/$1/device/new_device

#pcf85063
#echo "pcf85063 0x51" > /sys/class/i2c-dev/$1/device/new_device

