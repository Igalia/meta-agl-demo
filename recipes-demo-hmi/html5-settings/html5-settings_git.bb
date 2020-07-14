SUMMARY     = "AGL HTML5 Settings Application"
HOMEPAGE    = "https://git.automotivelinux.org/apps/html5-settings/"
SECTION     = "apps"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git/"

SRC_URI = "git://github.com/AGL-web-applications/settings.git;protocol=https;branch=WIP/agl-compositor"
SRCREV = "627e41507c013614e436d0f415e91f18385f2810"

DEPENDS = "nodejs-native"

inherit aglwgt
