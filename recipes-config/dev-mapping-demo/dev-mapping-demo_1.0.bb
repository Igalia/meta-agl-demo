SUMMARY = "AGL demo device mapping configuration file"
DESCRIPTION = "This provide default dev-mapping.conf file \
 that defines mapping between kernel device and logical name \
 used in low-can binding for the AGL demo setup."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI += "file://dev-mapping.conf.demo"

inherit allarch

do_install() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/dev-mapping.conf.demo ${D}${sysconfdir}/dev-mapping.conf
}

RPROVIDES_${PN} = "virtual/low-can-dev-mapping"
