SUMMARY     = "AGL HTML5 Mediaplayer Application"
HOMEPAGE    = "https://git.automotivelinux.org/apps/html5-mediaplayer/"
SECTION     = "apps"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git/"

SRC_URI = "git://github.com/AGL-web-applications/mediaplayer.git;protocol=https;branch=WIP/agl-compositor"
SRCREV = "3cf155d99a93d6dda5e2d0a4f6b35ef626402d04"

DEPENDS = "nodejs-native"

inherit aglwgt
