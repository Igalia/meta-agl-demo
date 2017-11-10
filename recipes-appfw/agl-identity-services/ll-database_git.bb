DESCRIPTION = "AGL low level user database binding"
HOMEPAGE = "https://github.com/iotbzh/agl-identity"
SECTION = "base"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=8089a3c40cff9caffd1b9ba5aa3dfd67"

SRC_URI = "gitsm://github.com/iotbzh/agl-identity.git;protocol=https;branch=master"
SRCREV  = "${AUTOREV}"

inherit cmake aglwgt pkgconfig

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git/ll-database-binding"

DEPENDS += " af-binder json-c gdbm "

