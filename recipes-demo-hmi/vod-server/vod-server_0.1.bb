DESCRIPTION = "Build VideoOnDemand server"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
#LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
#		   "  

PV = "0.1"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/vod-server;protocol=https"
SRC_URI += "file://0001-Fix-cross-compilation-with-yocto.patch"

S = "${WORKDIR}/git"
#SRCREV = "8c5f2324d7aa61669324aec1a0ad091fe1379489"
SRCREV = "${AUTOREV}"

do_install() {
        install -m 0755 -d ${D}${bindir} 
	install -m 0755 ${S}/VideoOnDemand ${D}${bindir}/VideoOnDemand
}

