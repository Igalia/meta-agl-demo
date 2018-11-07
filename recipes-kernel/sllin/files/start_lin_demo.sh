#!/bin/sh

sleep 1
ldattach 25 /dev/ttySC3
pidof ldattach > /var/run/lin_ldattach
sleep 1
ip link set sllin0 up
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

