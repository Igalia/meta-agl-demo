SUMMARY     = "AGL QtQuickControls2 style customizations"
HOMEPAGE    = "https://git.automotivelinux.org/gerrit/#/admin/projects/src/qtquickcontrols2-agl-style"
LICENSE     = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "qtquickcontrols2"

PV = "1.0+git${SRCPV}"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/qtquickcontrols2-agl-style;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AGL_APP_REVISION}"

S = "${WORKDIR}/git/"

inherit qmake5

FILES_${PN} += "${OE_QMAKE_PATH_QML}/QtQuick/Controls.2/AGL/*"

RDEPENDS_${PN} += " \
	qtquickcontrols-qmlplugins \
	qtquickcontrols2-qmlplugins \
	qtsvg-plugins \
"

do_install_append_class-target() {
    install -d ${D}/${sysconfdir}/afm/unit.env.d
    echo "QT_QUICK_CONTROLS_STYLE=AGL" > ${D}${sysconfdir}/afm/unit.env.d/qtquickcontrols2-style
}
