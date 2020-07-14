SUMMARY     = "AGL HTML5 Mixer Application"
HOMEPAGE    = "https://git.automotivelinux.org/apps/html5-mixer/"
SECTION     = "apps"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git/"

SRC_URI = "git://github.com/AGL-web-applications/mixer.git;protocol=https;branch=WIP/agl-compositor"
SRCREV = "6b3b014f7ec31a2d017e1eab1e02415dcaa45c39"

DEPENDS = "nodejs-native"

inherit aglwgt
