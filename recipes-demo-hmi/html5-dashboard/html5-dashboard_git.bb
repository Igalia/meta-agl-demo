SUMMARY     = "AGL HTML5 dashboard Application"
HOMEPAGE    = "https://git.automotivelinux.org/apps/html5-dashboard/"
SECTION     = "apps"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git/"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/html5-dashboard;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AGL_APP_REVISION}"

DEPENDS = "nodejs-native"

inherit aglwgt
