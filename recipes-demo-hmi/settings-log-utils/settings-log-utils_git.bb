SUMMARY     = "App for log record"
DESCRIPTION = "AGL HMI Application for demonstrating Log Record on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/settings-log-utils"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/settings-log-utils;protocol=https;branch=master"
SRCREV = "${AUTOREV}"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "libqtappfw qtquickcontrols2 qtwebsockets opencv boost"
DEPENDS += "libhomescreen qlibwindowmanager qtvirtualkeyboard"

# runtime dependencies
RDEPENDS_${PN} += " \
            agl-service-bluetooth \
            agl-service-network"

inherit qmake5 aglwgt
