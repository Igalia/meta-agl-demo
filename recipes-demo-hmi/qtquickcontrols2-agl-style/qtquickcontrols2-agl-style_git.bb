SUMMARY     = "AGL QtQuickControls2 style customizations"
HOMEPAGE    = "https://git.automotivelinux.org/gerrit/#/admin/projects/src/qtquickcontrols2-agl-style"
LICENSE     = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "qtquickcontrols2"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/qtquickcontrols2-agl-style;protocol=https;branch=dab"
SRCREV = "79991c0b1d17e38ad6188ab5fcb4c5ebd94ad059"
SRCREV_dab = "refs/tags/${DISTRO_BRANCH_VERSION_TAG}"


PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git/"

inherit qmake5

FILES_${PN} += "${OE_QMAKE_PATH_QML}/QtQuick/Controls.2/AGL/*"

RDEPENDS_${PN} += " \
	qtquickcontrols-qmlplugins \
	qtquickcontrols2-qmlplugins \
	qtsvg-plugins \
"
