SUMMARY     = "AGL HTML5 HVAC Application"
HOMEPAGE    = "https://git.automotivelinux.org/apps/html5-hvac/"
SECTION     = "apps"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git/"

SRC_URI = "git://github.com/AGL-web-applications/hvac.git;protocol=https;branch=WIP/agl-compositor"
SRCREV = "ca60d367a6016b8cd9af6b73b1d24881980f4592"

DEPENDS = "nodejs-native"

inherit aglwgt
