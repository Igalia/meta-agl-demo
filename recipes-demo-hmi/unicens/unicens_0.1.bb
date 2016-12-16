DESCRIPTION = "Build NetworkManager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
#LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
#		   "  

PV = "0.1"

DEPENDS += "libxml2"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/unicens;protocol=https"
SRC_URI += "file://0001-Fix-cross-compilation-with-yocto.patch"

S = "${WORKDIR}/git"
#SRCREV = "8c5f2324d7aa61669324aec1a0ad091fe1379489"
SRCREV = "${AUTOREV}"

do_install() {
        install -m 0755 -d ${D}${bindir} ${D}/home/root 
	install -m 0755 ${S}/NetworkManager ${D}${bindir}/MostNetworkManager
	install -m 0644 ${S}/scripts/config-agl.xml ${D}/home/root
	install -m 0644 ${S}/scripts/camera-os88122-ts.script ${D}/home/root
	install -m 0644 ${S}/scripts/i2c-slim-amplifier-v2.3.script ${D}/home/root
	install -m 0644 ${S}/scripts/i2c-uda1388-v2.3.script ${D}/home/root
	install -m 0755	${S}/scripts/loadDriver.sh ${D}/home/root
}
FILES_${PN} += "/home/root*"
