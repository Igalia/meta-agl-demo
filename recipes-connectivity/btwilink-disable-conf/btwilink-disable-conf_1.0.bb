SUMMARY = "btwilink module disabling modprobe configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://btwilink-disable.conf"

COMPATIBLE_MACHINE = "m3ulcb|h3ulcb"

do_compile[noexec] = "1"

do_install() {
    install -d ${D}${sysconfdir}/modprobe.d
    install -m 0644 ${WORKDIR}/btwilink-disable.conf ${D}${sysconfdir}/modprobe.d
}
