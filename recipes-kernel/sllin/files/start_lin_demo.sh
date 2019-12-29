#!/bin/sh

# Attach serial LIN->CAN bridge and set up LIN polling
if [ -c /dev/ttyUSB0 ]; then
    sleep 1
    /usr/bin/lin_config -c /etc/lin_config.conf -a sllin:/dev/ttyUSB0
    pidof lin_config > /var/run/lin_config.pid
    sleep 1
else
    ip link add dev sllin0 type vcan
fi
ip link set sllin0 up

# Initialize HVAC controller
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 02A#
usleep 100000
cansend sllin0 02B#
usleep 100000
cansend sllin0 032#F0F8FFFFFFFFFFFF
usleep 100000
cansend sllin0 032#F0F8FFFFFFFFFFFF
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 030#
usleep 100000
cansend sllin0 02D#
usleep 100000
cansend sllin0 02C#
usleep 100000
cansend sllin0 02D#
usleep 100000
cansend sllin0 02C#
usleep 100000

