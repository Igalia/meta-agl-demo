DESCRIPTION = "Example of how to build an external Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
		    file://core.c;md5=a47d4154c42744ac4ea233372ddf975d \
		   "  

inherit module

PR = "r0"
PV = "0.1"

SRC_URI = "file://Makefile \
	   file://core.c \
	   file://mostcore.h \
	   file://COPYING \
	  "

S = "${WORKDIR}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
