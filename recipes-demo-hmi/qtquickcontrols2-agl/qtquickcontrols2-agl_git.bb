SUMMARY     = "AGL demo custom QtQuickControls2 widgets"
HOMEPAGE    = "https://git.automotivelinux.org/gerrit/#/admin/projects/src/qtquickcontrols2-agl"
LICENSE     = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "qtquickcontrols2"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/qtquickcontrols2-agl;protocol=https;branch=dab"
SRCREV = "b931bb74bf2025ad1d29052035c61e25570413e9"
SRCREV_dab = "refs/tags/${DISTRO_BRANCH_VERSION_TAG}"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git/"

inherit qmake5

FILES_${PN} += "${OE_QMAKE_PATH_QML}/AGL/Demo/Controls/*"

RDEPENDS_${PN} += " \
	qtquickcontrols-qmlplugins \
	qtquickcontrols2-qmlplugins \
	qtsvg-plugins \
"
