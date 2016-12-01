SUMMARY = "MMDAgent Voice Data."
HOMEPAGE = "https://sourceforge.net/projects/mmdagent/"
SECTION = "libs"
LICENSE = "CC-BY-3.0"

inherit allarch

LIC_FILES_CHKSUM = "file://Voice/mei/COPYRIGHT.txt;md5=4efd58b40631c9d879e28ce678480081"

SRC_URI = "http://downloads.sourceforge.net/project/mmdagent/MMDAgent_Example/MMDAgent_Example-${PV}/MMDAgent_Example-${PV}.zip "

SRC_URI[md5sum] = "b95a1ae9897a17cfac55b913565d981a"
SRC_URI[sha256sum] = "2640ede5831a83e19f9cd8dabca9ad07ef05c50af06c6bc8cb3adfb5e5d4f639"

S = "${WORKDIR}/MMDAgent_Example-${PV}"

do_install() {
    install -m 0755 -d ${D}/${datadir}/Voice/mei
    install -m 0644 -p ${S}/Voice/mei/*.htsvoice ${D}/${datadir}/Voice/mei/
}

FILES_${PN} += " ${datadir}/Voice/mei/*.htsvoice "
