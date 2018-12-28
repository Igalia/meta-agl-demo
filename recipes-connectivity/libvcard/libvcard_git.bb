DESCRIPTION = "Qt vCard library"
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f94eaed54ffa1718d593504bae740faf"

DEPENDS += "qtbase"

SRCREV = "9b43ca01865f171cdd8a7d47b6da7c1a41c03e38"
SRC_URI = "git://github.com/pol51/libvcard.git;protocol=https"

PV = "1.0+gitr${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake_qt5

ALLOW_EMPTY_${PN} = "1"
