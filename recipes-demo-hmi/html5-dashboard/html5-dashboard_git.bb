SUMMARY     = "AGL HTML5 dashboard Application"
HOMEPAGE    = "https://git.automotivelinux.org/apps/html5-dashboard/"
SECTION     = "apps"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git/"

SRC_URI = "git://github.com/AGL-web-applications/dashboard.git;protocol=https;branch=WIP/agl-compositor"
SRCREV = "72912c13081dc48ccb16725c6e14ba1eb453ac35"

DEPENDS = "nodejs-native"

inherit aglwgt
