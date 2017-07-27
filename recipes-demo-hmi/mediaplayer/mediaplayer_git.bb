SUMMARY     = "Media Player for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating Media Player on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/mediaplayer"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/mediaplayer;protocol=https;branch=dab"
SRCREV  = "71b6e40301a2e23edfeaea5c33187db14c4d8917"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit qmake5 aglwgt

# build-time dependencies
DEPENDS += "sqlite3 qtquickcontrols2 qtmultimedia"

RDEPENDS_${PN} += "qtmultimedia qtmultimedia-qmlplugins lightmediascanner"
