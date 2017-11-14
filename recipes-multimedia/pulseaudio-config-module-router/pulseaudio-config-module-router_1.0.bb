SUMMARY = "PulseAudio configuration to enable router module"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://20-module-router.pa"

do_install () {
    install -d ${D}${sysconfdir}/pulse/default.d
    install -m 0644 ${WORKDIR}/20-module-router.pa ${D}${sysconfdir}/pulse/default.d/
}

RDEPENDS_${PN} = "module-router"
RPROVIDES_${PN} = "virtual/pulseaudio-config"
