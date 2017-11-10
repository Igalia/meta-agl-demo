DESCRIPTION = "AGL low level user database binding"
HOMEPAGE = "https://github.com/iotbzh/nfc-binding"
SECTION = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "gitsm://github.com/iotbzh/nfc-binding.git;protocol=https;branch=master"
SRCREV  = "${AUTOREV}"

inherit cmake aglwgt pkgconfig

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"

DEPENDS += " af-binder json-c libnfc "

