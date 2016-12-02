DESCRIPTION = "Example of how to build an external Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV = "2.0.4"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/can-lin/;protocol=https;branch=blowfish"
S = "${WORKDIR}/git/Usb-Driver"
SRCREV = "cb3fee430d40f64a4897440e269f9feda189cbed"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
