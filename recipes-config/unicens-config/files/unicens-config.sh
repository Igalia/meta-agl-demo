#!/bin/bash

##########################################################################
# NOTE: This script is intentended to be used with the MOST starter-kit[1]
# in conjuction with the agl-service-unicens widget only. For other setups
# this script and also the UNICENS config settings need to be adapted
# accordingly.
#
# An optional approach to configure the driver is provided by the
# default_conf.ko kernel module that can be found in
# /lib/modules/`uname -r`/extra
#
#
# [1]: The starter-kit consists of one Network Interface Controller and
# three slim amplifier modules)
##########################################################################


##########################################
# interface: mdev0
# chip: Vantage
# aim: cdev
# name: inic-usb-crx
echo rx > /sys/devices/virtual/most/mostcore/devices/mdev0/ep8f/set_direction 2>/dev/null
echo control > /sys/devices/virtual/most/mostcore/devices/mdev0/ep8f/set_datatype 2>/dev/null
echo 16 > /sys/devices/virtual/most/mostcore/devices/mdev0/ep8f/set_number_of_buffers 2>/dev/null
echo 64 > /sys/devices/virtual/most/mostcore/devices/mdev0/ep8f/set_buffer_size 2>/dev/null

echo mdev0:ep8f:inic-usb-crx > /sys/devices/virtual/most/mostcore/aims/cdev/add_link 2>/dev/null

#aim: cdev
#name: inic-usb-ctx
echo tx > /sys/devices/virtual/most/mostcore/devices/mdev0/ep0f/set_direction 2>/dev/null
echo control > /sys/devices/virtual/most/mostcore/devices/mdev0/ep0f/set_datatype 2>/dev/null
echo 16 > /sys/devices/virtual/most/mostcore/devices/mdev0/ep0f/set_number_of_buffers 2>/dev/null
echo 64 > /sys/devices/virtual/most/mostcore/devices/mdev0/ep0f/set_buffer_size 2>/dev/null

echo mdev0:ep0f:inic-usb-ctx > /sys/devices/virtual/most/mostcore/aims/cdev/add_link 2>/dev/null

##########################################
# interface: mdev1
#aim: networking
#name: inic-usb-arx
echo rx > /sys/devices/virtual/most/mostcore/devices/mdev1/ep8e/set_direction 2>/dev/null
echo async > /sys/devices/virtual/most/mostcore/devices/mdev1/ep8e/set_datatype 2>/dev/null
echo 20 > /sys/devices/virtual/most/mostcore/devices/mdev1/ep8e/set_number_of_buffers 2>/dev/null
echo 1522 > /sys/devices/virtual/most/mostcore/devices/mdev1/ep8e/set_buffer_size 2>/dev/null

echo mdev1:ep8e:inic-usb-arx > /sys/devices/virtual/most/mostcore/aims/networking/add_link 2>/dev/null
#aim: networking
#name: inic-usb-atx
echo tx > /sys/devices/virtual/most/mostcore/devices/mdev1/ep0e/set_direction 2>/dev/null
echo async > /sys/devices/virtual/most/mostcore/devices/mdev1/ep0e/set_datatype 2>/dev/null
echo 20 > /sys/devices/virtual/most/mostcore/devices/mdev1/ep0e/set_number_of_buffers 2>/dev/null
echo 1522 > /sys/devices/virtual/most/mostcore/devices/mdev1/ep0e/set_buffer_size 2>/dev/null

echo mdev1:ep0e:inic-usb-atx > /sys/devices/virtual/most/mostcore/aims/networking/add_link 2>/dev/null
###########################################
## interface: mdev0
## chip: Durango
##aim: cdev
##name: inic-usb-crx
echo rx > /sys/devices/virtual/most/mostcore/devices/mdev0/ep87/set_direction 2>/dev/null
echo control > /sys/devices/virtual/most/mostcore/devices/mdev0/ep87/set_datatype 2>/dev/null
echo 16 > /sys/devices/virtual/most/mostcore/devices/mdev0/ep87/set_number_of_buffers 2>/dev/null
echo 64 > /sys/devices/virtual/most/mostcore/devices/mdev0/ep87/set_buffer_size 2>/dev/null

echo mdev0:ep87:inic-usb-crx > /sys/devices/virtual/most/mostcore/aims/cdev/add_link 2>/dev/null
#aim: cdev
#name: inic-usb-ctx
echo tx > /sys/devices/virtual/most/mostcore/devices/mdev0/ep07/set_direction 2>/dev/null
echo control > /sys/devices/virtual/most/mostcore/devices/mdev0/ep07/set_datatype 2>/dev/null
echo 16 > /sys/devices/virtual/most/mostcore/devices/mdev0/ep07/set_number_of_buffers 2>/dev/null
echo 64 > /sys/devices/virtual/most/mostcore/devices/mdev0/ep07/set_buffer_size 2>/dev/null

echo mdev0:ep07:inic-usb-ctx > /sys/devices/virtual/most/mostcore/aims/cdev/add_link 2>/dev/null
###########################################
## interface: mdev1
## chip: Durango
## aim: networking
##name: inic-usb-arx
echo rx > /sys/devices/virtual/most/mostcore/devices/mdev1/ep86/set_direction 2>/dev/null
echo async > /sys/devices/virtual/most/mostcore/devices/mdev1/ep86/set_datatype 2>/dev/null
echo 20 > /sys/devices/virtual/most/mostcore/devices/mdev1/ep86/set_number_of_buffers 2>/dev/null
echo 1522 > /sys/devices/virtual/most/mostcore/devices/mdev1/ep86/set_buffer_size 2>/dev/null

echo mdev1:ep86:inic-usb-arx > /sys/devices/virtual/most/mostcore/aims/networking/add_link 2>/dev/null
#aim: networking
#name: inic-usb-atx
echo tx > /sys/devices/virtual/most/mostcore/devices/mdev1/ep06/set_direction 2>/dev/null
echo async > /sys/devices/virtual/most/mostcore/devices/mdev1/ep06/set_datatype 2>/dev/null
echo 20 > /sys/devices/virtual/most/mostcore/devices/mdev1/ep06/set_number_of_buffers 2>/dev/null
echo 1522 > /sys/devices/virtual/most/mostcore/devices/mdev1/ep06/set_buffer_size 2>/dev/null

echo mdev1:ep06:inic-usb-atx > /sys/devices/virtual/most/mostcore/aims/networking/add_link 2>/dev/null

##########################################
# interface: mdev2
#aim: sound
#name: ep01-6ch.6x16
echo tx > /sys/devices/virtual/most/mostcore/devices/mdev2/ep01/set_direction 2>/dev/null
echo sync > /sys/devices/virtual/most/mostcore/devices/mdev2/ep01/set_datatype 2>/dev/null
echo 16 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep01/set_number_of_buffers 2>/dev/null
echo 1008 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep01/set_buffer_size 2>/dev/null
echo 12 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep01/set_subbuffer_size 2>/dev/null
echo 42 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep01/set_packets_per_xact 2>/dev/null

echo mdev2:ep01:ep01-6ch.6x16 > /sys/devices/virtual/most/mostcore/aims/sound/add_link 2>/dev/null
##aim: cdev
##name: inic-usb-itx1
echo tx > /sys/devices/virtual/most/mostcore/devices/mdev2/ep02/set_direction 2>/dev/null
echo isoc > /sys/devices/virtual/most/mostcore/devices/mdev2/ep02/set_datatype 2>/dev/null
echo 8 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep02/set_number_of_buffers 2>/dev/null
echo 7520 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep02/set_buffer_size 2>/dev/null
echo 188 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep02/set_subbuffer_size 2>/dev/null
echo 2 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep02/set_packets_per_xact 2>/dev/null

echo mdev2:ep02:inic-usb-itx1 > /sys/devices/virtual/most/mostcore/aims/cdev/add_link 2>/dev/null

##aim: sound
##name: ep02-2ch.2x16
#echo tx > /sys/devices/virtual/most/mostcore/devices/mdev2/ep02/set_direction
#echo sync > /sys/devices/virtual/most/mostcore/devices/mdev2/ep02/set_datatype
#echo 4 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep02/set_number_of_buffers
#echo 1024 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep02/set_buffer_size
#echo 4 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep01/set_subbuffer_size
#echo 128 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep01/set_packets_per_xact
#
#echo mdev2:ep02:ep02-2ch.2x16 > /sys/devices/virtual/most/mostcore/aims/sound/add_link
##aim: sound
##name: ep81-6ch.6x16
#echo tx > /sys/devices/virtual/most/mostcore/devices/mdev2/ep81/set_direction
#echo sync > /sys/devices/virtual/most/mostcore/devices/mdev2/ep81/set_datatype
#echo 4 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep81/set_number_of_buffers
#echo 1008 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep81/set_buffer_size
#echo 12 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep81/set_subbuffer_size
#echo 42 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep81/set_packets_per_xact
#
#echo mdev2:ep81:ep81-6ch.6x16 > /sys/devices/virtual/most/mostcore/aims/sound/add_link
##aim: sound
##name: ep82-6ch.6x16
#echo tx > /sys/devices/virtual/most/mostcore/devices/mdev2/ep82/set_direction
#echo sync > /sys/devices/virtual/most/mostcore/devices/mdev2/ep82/set_datatype
#echo 4 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep82/set_number_of_buffers
#echo 1008 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep81/set_buffer_size
#echo 12 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep82/set_subbuffer_size
#echo 42 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep82/set_packets_per_xact
#
#echo mdev2:ep82:ep82-6ch.6x16 > /sys/devices/virtual/most/mostcore/aims/sound/add_link
##aim: sound
##name: ep83-2ch.2x16
#echo tx > /sys/devices/virtual/most/mostcore/devices/mdev2/ep83/set_direction
#echo sync > /sys/devices/virtual/most/mostcore/devices/mdev2/ep83/set_datatype
#echo 4 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep83/set_number_of_buffers
#echo 1024 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep83/set_buffer_size
#echo 4 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep83/set_subbuffer_size
#echo 128 > /sys/devices/virtual/most/mostcore/devices/mdev2/ep83/set_packets_per_xact
#
#echo mdev2:ep83:ep83-2ch.2x16 > /sys/devices/virtual/most/mostcore/aims/sound/add_link

