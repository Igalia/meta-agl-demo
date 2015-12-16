DESCRIPTION = "Example of how to build an external Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
		    file://networking.c;md5=b8382020c8f8164f883cdb311b66ae69 \ 
		   "

inherit module

PR = "r0"
PV = "0.1"

SRC_URI = "file://Makefile \
	   file://../../mostcore/files/mostcore.h \
	   file://networking.h \
	   file://networking.c \
	   file://COPYING \
	  "

S = "${WORKDIR}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
