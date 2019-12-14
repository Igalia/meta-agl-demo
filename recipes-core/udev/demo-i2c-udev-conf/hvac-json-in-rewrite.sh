#!/bin/bash

# (C) 2018 Jan-Simon Möller, dl9pf@gmx.de, jsmoeller@linuxfoundation.org
# License: Apache License 2.0

#set -x
set -e

if [ $1 ] ; then
    # The device is always 0009 -> 9 . Only change is the i2c IF .
    LED=`echo $1 | sed -e "s#0009#9#g"`
    if [ $? -eq 0 ] ; then
	echo "$LED"
	sed -e "s#@DEVICE@#$LED#" /etc/hvac.json.in > /etc/hvac.json
    else
	echo "Invalid argument"
	exit 1
    fi
else
    echo "Need argument"
    exit 1
fi