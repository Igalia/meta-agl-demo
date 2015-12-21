DESCRIPTION = "Example of how to build an external Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
		    file://sound.c;md5=5306a2a73f0855ecf71a8241796517a6 \ 
		   "

inherit module

PR = "r0"
PV = "0.1"

SRC_URI = "file://Makefile \
	   file://../../mostcore/files/mostcore.h \
	   file://sound.c \
	   file://COPYING \
	  "

S = "${WORKDIR}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
