SUMMARY     = "App Launcher for the AGL Demonstrator @ CES2017"
DESCRIPTION = "App Lanucher app in QML format for the AGL Demonstrator @ CESS2017"
HOMEPAGE    = "https://git.automotivelinux.org/gerrit/#/admin/projects/AGL/DemoApps/CES2017"
SECTION     = "apps"
LICENSE     = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "homescreen zip-native qtmultimedia qtquickcontrols2"

PV = "1.0+git${SRCPV}"
PN = "ces2017-demo"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/AGL/DemoApps/CES2017;protocol=http"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/"

inherit qmake5

do_install() {
    install -d ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/qmldir ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/ImageButton.qml ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/ToggleButton.qml ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/Key.qml ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/AbstractKeyboard.qml ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/Symbols.qml ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/Alphabet.qml ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/Keyboard.qml ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -d ${D}${libdir}/qt5/qml/AGL/Demo/Controls/images/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/images/Keyboard_Back.svg ${D}${libdir}/qt5/qml/AGL/Demo/Controls/images/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/images/Keyboard_Shift.svg ${D}${libdir}/qt5/qml/AGL/Demo/Controls/images/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/images/Keyboard_Space.svg ${D}${libdir}/qt5/qml/AGL/Demo/Controls/images/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/images/Keyboard_Arrow.svg ${D}${libdir}/qt5/qml/AGL/Demo/Controls/images/

    install -d ${D}${libdir}/qt5/qml/QtQuick/Controls.2/AGL
    install -m 0644 ${S}/imports/qtquickcontrols2aglstyle/*.qml ${D}${libdir}/qt5/qml/QtQuick/Controls.2/AGL

    install -d ${D}${libdir}/qt5/qml/QtQuick/Controls.2/AGL/images/
    install -m 0644 ${S}/imports/qtquickcontrols2aglstyle/images/* ${D}${libdir}/qt5/qml/QtQuick/Controls.2/AGL/images/
}

FILES_${PN} += " \
	/usr/AGL/ \
	/usr/lib/qt5/qml/AGL/Demo/Controls/qmldir \
	/usr/lib/qt5/qml/AGL/Demo/Controls/ImageButton.qml \
	/usr/lib/qt5/qml/AGL/Demo/Controls/ToggleButton.qml \
	/usr/lib/qt5/qml/AGL/Demo/Controls/Key.qml \
	/usr/lib/qt5/qml/AGL/Demo/Controls/AbstractKeyboard.qml \
	/usr/lib/qt5/qml/AGL/Demo/Controls/Symbols.qml \
	/usr/lib/qt5/qml/AGL/Demo/Controls/Alphabet.qml \
	/usr/lib/qt5/qml/AGL/Demo/Controls/Keyboard.qml \
	/usr/lib/qt5/qml/AGL/Demo/Controls/images/Keyboard_Back.svg \
	/usr/lib/qt5/qml/AGL/Demo/Controls/images/Keyboard_Shift.svg \
	/usr/lib/qt5/qml/AGL/Demo/Controls/images/Keyboard_Space.svg \
	/usr/lib/qt5/qml/AGL/Demo/Controls/images/Keyboard_Arrow.svg \
	/usr/lib/qt5/qml/QtQuick/Controls.2/AGL \
	/usr/lib/qt5/qml/QtQuick/Controls.2/AGL/images \
"

RDEPENDS_${PN} += " \
	qtquickcontrols-qmlplugins \
	qtquickcontrols2-qmlplugins \
	qtsvg-plugins \
"
