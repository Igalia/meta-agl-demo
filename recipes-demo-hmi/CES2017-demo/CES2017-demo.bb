SUMMARY     = "App Launcher for the AGL Demonstrator @ CES2017"
DESCRIPTION = "App Lanucher app in QML format for the AGL Demonstrator @ CESS2017"
HOMEPAGE    = "https://git.automotivelinux.org/gerrit/#/admin/projects/AGL/DemoApps/CES2017"
LICENSE     = "MPL-2.0"
SECTION     = "apps"
PV          = "1.0+git${SRCPV}"
S           = "${WORKDIR}/git/"
PN          = "ces2017-demo"

inherit qmake5
DEPENDS = "homescreen zip-native qtmultimedia qtquickcontrols2"

inherit aglwgt

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

# ALS, CES, FOSDEM available
AGL_RADIO_PRESETS_LOCALE ?= "CES"

SRC_URI     = "git://gerrit.automotivelinux.org/gerrit/AGL/DemoApps/CES2017;protocol=http \
               file://presets-ALS.conf \
               file://presets-CES.conf \
               file://presets-FOSDEM.conf \
              "
SRCREV      = "${AUTOREV}"

# Pinned branch/SRCREV for Charming Chinook
SRC_URI_chinook = "git://gerrit.automotivelinux.org/gerrit/AGL/DemoApps/CES2017;protocol=http;branch=chinook \
                   file://presets-ALS.conf \
                   file://presets-CES.conf \
                   file://presets-FOSDEM.conf \
                  "
SRCREV_chinook = "354195c83841240ddd5f2c5daad97d66cc9e1d28"

RDEPENDS_${PN} += " \
    qtmultimedia-qmlplugins \
    qtmultimedia-rtlfm-radio-plugin \
    qtquickcontrols-qmlplugins \
    qtquickcontrols2-qmlplugins \
    qtsvg-plugins \
    "

do_install_prepend() {
    mkdir -p ${B}/package

    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \
<widget xmlns=\"http://www.w3.org/ns/widgets\" id=\"controls\" version=\"0.1\"> \
  <name>Controls</name> \
  <content src=\"controls\" type=\"application/x-executable\"/> \
  <description>Controls app.</description> \
  <author>Qt</author> \
  <icon src=\"controls.png\"/> \
  <license>Apache 2.0</license> \
</widget> \
" > ${B}/apps/Controls/config.xml

    cd ${B}/apps/Controls/
    zip ${B}/package/controls.wgt config.xml controls

    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \
<widget xmlns=\"http://www.w3.org/ns/widgets\" id=\"dashboard\" version=\"0.1\"> \
  <name>Dashboard</name> \
  <content src=\"dashboard\" type=\"application/x-executable\"/> \
  <description>Dashboard app.</description> \
  <author>Qt</author> \
  <icon src=\"dashboard.png\"/> \
  <license>Apache 2.0</license> \
</widget> \
" > ${B}/apps/Dashboard/config.xml

    cd ${B}/apps/Dashboard/
    zip ${B}/package/dashboard.wgt config.xml dashboard


    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \
<widget xmlns=\"http://www.w3.org/ns/widgets\" id=\"phone\" version=\"0.1\"> \
  <name>Phone</name> \
  <content src=\"phone\" type=\"application/x-executable\"/> \
  <description>Phone app.</description> \
  <author>Qt</author> \
  <icon src=\"phone.png\"/> \
  <license>Apache 2.0</license> \
</widget> \
" > ${B}/apps/Phone/config.xml

    cd ${B}/apps/Phone/
    zip ${B}/package/phone.wgt config.xml phone



    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \
<widget xmlns=\"http://www.w3.org/ns/widgets\" id=\"radio\" version=\"0.1\"> \
  <name>Radio</name> \
  <content src=\"radio\" type=\"application/x-executable\"/> \
  <description>Radio app.</description> \
  <author>Qt</author> \
  <icon src=\"radio.png\"/> \
  <license>Apache 2.0</license> \
</widget> \
" > ${B}/apps/Radio/config.xml

    cd ${B}/apps/Radio/
    zip ${B}/package/radio.wgt config.xml radio

}

do_install() {
    install -d ${D}/home/root/app-data/radio
    install -m 0644 ${WORKDIR}/presets-CES.conf ${D}/home/root/app-data/radio/
    install -m 0644 ${WORKDIR}/presets-ALS.conf ${D}/home/root/app-data/radio/
    install -m 0644 ${WORKDIR}/presets-FOSDEM.conf ${D}/home/root/app-data/radio/
    install -m 0644 ${WORKDIR}/presets-${AGL_RADIO_PRESETS_LOCALE}.conf ${D}/home/root/app-data/radio/presets.conf

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

# plain copy in own folder for now
#do_install() {
#    mkdir -p ${D}/usr/AGL/CES2017/
#    cp -rf ./* ${D}/usr/AGL/CES2017/
#}

FILES_${PN} += "/usr/AGL/ \
        /home/root/app-data/radio/presets-*.conf \
        /home/root/app-data/radio/presets.conf \
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
