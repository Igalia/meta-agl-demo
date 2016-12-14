DESCRIPTION = "CAN-LIN Application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PR = "r0"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/staging/most-can-demo-bin;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "70458963c48662012af856b56676260150f124c8"

do_configure() {
        echo ""
}

do_compile() {
        echo ""
}

do_install() {
        install -m 0755 -d ${D}${bindir}
        install -m 0755 ${S}/OptoLyzerMoccaApp ${D}${bindir}
}

