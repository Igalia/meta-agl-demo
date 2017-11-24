SUMMARY     = "AGL Home Screen Library"
DESCRIPTION = "libhomescreen"
HOMEPAGE    = "http://docs.automotivelinux.org"
LICENSE     = "Apache-2.0"
SECTION     = "libs"

BBCLASSEXTEND = " nativesdk"

LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

DEPENDS = "af-binder json-c"

inherit cmake

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/p/src/libhomescreen.git;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "d97eb6052b7652a42d502e5887bd7acf3fc6d91b"
S = "${WORKDIR}/git"

RDEPENDS_${PN} = "agl-service-homescreen-2017"
PROVIDES += "virtual/libhomescreen"
