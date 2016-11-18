SUMMARY = "HTS voice."
HOMEPAGE = "https://sourceforge.net/projects/mmdagent/"
SECTION = "libs"
LICENSE = "CC-BY-3.0"

inherit allarch

LIC_FILES_CHKSUM = "file://COPYING;md5=46a53fe79125e971ec81d84e6586f4c8"

SRC_URI = "http://downloads.sourceforge.net/hts-engine/hts_voice_cmu_us_arctic_slt-${PV}.tar.gz"

SRC_URI[md5sum] = "0d76f505e7fbc5fa68a437d29f591dcd"
SRC_URI[sha256sum] = "3aa86d810e04548814b83db36a1d5a187d95a136890a378913d0b1b97ef90ba4"

S = "${WORKDIR}/hts_voice_cmu_us_arctic_slt-${PV}"

do_install() {
    install -m 0755 -d ${D}/${datadir}/Voice/us
    install -m 0644 -p ${S}/*.htsvoice ${D}/${datadir}/Voice/us/
}

FILES_${PN} += " ${datadir}/Voice/us/*.htsvoice "
