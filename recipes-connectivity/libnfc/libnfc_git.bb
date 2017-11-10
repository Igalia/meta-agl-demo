SUMMARY     = "LibNFC "
DESCRIPTION = "Platform independent Near Field Communication (NFC) library"
HOMEPAGE    = "http://nfc-tools.org/"

LICENSE     = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=b52f2d57d10c4f7ee67a7eb9615d5d24"

DEPENDS = "libusb"

SRC_URI = "git://github.com/nfc-tools/libnfc.git;protocol=https;branch=master"
SRCREV = "2d4543673e9b76c02679ca8b89259659f1afd932"

PV = "4.0+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit cmake pkgconfig

