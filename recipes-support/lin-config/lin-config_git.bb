DESCRIPTION = "lin-config tool for the sllin driver module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"


DEPENDS += "libnl libxml2"

SRC_URI = "git://github.com/trainman419/linux-lin.git;protocol=https"
SRCREV = "155d885e8ccc907a56f6c86c4b159fac27ef6fec"
S = "${WORKDIR}/git/lin_config/src"

SRC_URI_append = " \
	file://0001-Change-Makefile-to-use-pkg-config-for-libxml-2.0.patch \
	"

inherit pkgconfig

PV = "0.1+git${SRCPV}"


do_configure[noexec] = "1"

do_install_append() {

    install -d ${D}/${bindir}
    install -m 644 ${S}/lin_config ${D}/${bindir}
}