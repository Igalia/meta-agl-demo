DESCRIPTION = "AGL low level user database binding"
HOMEPAGE = "https://github.com/iotbzh/nfc-binding"
SECTION = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/iotbzh/agl-spotify-app.git;protocol=https;branch=master"
SRCREV  = "${AUTOREV}"

inherit aglwgt

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"

DEPENDS += " zip-native "
RDEPENDS_${PN} += " agl-identity-agent agl-spotify-binding "

POST_INSTALL_LEVEL = "90"
