DESCRIPTION = "Example of how to build an external Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
		    file://dim2_hdm.c;md5=263233c0fde1611b5b416f415bd3594d \  
		    file://dim2_hal.c;md5=9ae71bc76a4a056593eec2efc5703f50 \
		    file://dim2_sysfs.c;md5=d66a0b6e9b3979d18653029d1cb4c362 \  
		   "

inherit module

PR = "r0"
PV = "0.1"

SRC_URI = "file://Makefile \
	   file://../../mostcore/files/mostcore.h \
	   file://../../aim-network/files/networking.h \
	   file://dim2_hal.h \
	   file://dim2_hdm.h \
	   file://dim2_sysfs.h \
	   file://dim2_reg.h \
	   file://dim2_errors.h \
	   file://dim2_hdm.c \
	   file://dim2_hal.c \
	   file://dim2_sysfs.c \
	   file://COPYING \
	  "

S = "${WORKDIR}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
