LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

DEPENDS = "af-binder json-c"

inherit cmake

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/p/src/libhomescreen.git;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "390902a6b002c6af183c939749fdebfcc4b3e839"
S = "${WORKDIR}/git"

RDEPENDS_${PN} = "agl-service-homescreen-2017"
