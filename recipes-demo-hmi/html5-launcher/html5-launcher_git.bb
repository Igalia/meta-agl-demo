SUMMARY     = "AGL HTML5 Launcher Application"
HOMEPAGE    = "https://git.automotivelinux.org/apps/html5-launcher/"
SECTION     = "apps"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git/"

SRC_URI = "git://github.com/AGL-web-applications/launcher.git;protocol=https;branch=WIP/agl-compositor"
SRCREV = "f0422c17371cc5350aa42f6a8b61e0703a35a5d5"

DEPENDS = "nodejs-native"

inherit aglwgt
