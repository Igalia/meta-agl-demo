SUMMARY     = "libnfc"
DESCRIPTION = "Platform independent Near Field Communication (NFC) library"
HOMEPAGE    = "https://github.com/nfc-tools/libnfc"
SECTION     = "apps"

DEPENDS     = "libusb"

LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=b52f2d57d10c4f7ee67a7eb9615d5d24"

inherit cmake pkgconfig

SRC_URI = "git://github.com/nfc-tools/libnfc;protocol=https;branch=master"
SRCREV = "2d4543673e9b76c02679ca8b89259659f1afd932"

PV = "1.7.1+git${SRCPV}"
S  = "${WORKDIR}/git"

