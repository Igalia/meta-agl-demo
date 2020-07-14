SUMMARY     = "AGL HTML5 Homescreen Application"
HOMEPAGE    = "https://git.automotivelinux.org/apps/html5-homescreen/"
SECTION     = "apps"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git/"

SRC_URI = "git://github.com/AGL-web-applications/homescreen.git;protocol=https;branch=WIP/agl-compositor"
SRCREV = "e62789889bb2d21b199026fa9f9baf4eddff6911"

DEPENDS = "nodejs-native"

inherit aglwgt
