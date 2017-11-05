SUMMARY     = "AGL demo custom QtQuickControls2 widgets"
HOMEPAGE    = "https://git.automotivelinux.org/gerrit/#/admin/projects/src/qtquickcontrols2-agl"
LICENSE     = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "qtquickcontrols2"

PV = "1.0+git${SRCPV}"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/qtquickcontrols2-agl;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AGL_APP_REVISION}"

S = "${WORKDIR}/git/"

inherit qmake5

FILES_${PN} += "${OE_QMAKE_PATH_QML}/AGL/Demo/Controls/*"

RDEPENDS_${PN} += " \
	qtquickcontrols-qmlplugins \
	qtquickcontrols2-qmlplugins \
	qtsvg-plugins \
"
