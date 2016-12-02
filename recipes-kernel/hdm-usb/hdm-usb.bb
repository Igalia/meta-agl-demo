DESCRIPTION = "Example of how to build an external Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
#LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
#		   "  

inherit module

PV = "2.0.4"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/most;protocol=https;branch=blowfish"

S = "${WORKDIR}/git/driver/${PN}"
SRCREV = "f70e1e1bab1608a2696fa5450641043dc4961ea8"
#SRCREV = "${AUTOREV}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
