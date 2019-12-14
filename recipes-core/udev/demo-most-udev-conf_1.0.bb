SUMMARY = "MOST demo hardware udev configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://99-agl-fibredyne-amp.rules \
           file://enable-agl-demo-hal.sh \
"

do_compile[noexec] = "1"

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 ${WORKDIR}/enable-agl-demo-hal.sh ${D}${sbindir}

    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/99-agl-fibredyne-amp.rules ${D}${sysconfdir}/udev/rules.d/
}

RDEPENDS_${PN} += "bash"
