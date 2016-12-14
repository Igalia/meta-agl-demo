SUMMARY     = "App Launcher for the AGL Demonstrator @ CES2017"
DESCRIPTION = "App Lanucher app in QML format for the AGL Demonstrator @ CESS2017"
HOMEPAGE    = "https://git.automotivelinux.org/gerrit/#/admin/projects/AGL/DemoApps/CES2017"
LICENSE     = "MPL-2.0"
SECTION     = "apps"
PV          = "1.0+git${SRCPV}"
S           = "${WORKDIR}/git/"
PN          = "ces2017-demo"

inherit qmake5
DEPENDS = " qtbase qtdeclarative qtquickcontrols2 qtvirtualkeyboard qtmultimedia homescreen zip-native"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI     = "git://gerrit.automotivelinux.org/gerrit/AGL/DemoApps/CES2017;protocol=http"
SRCREV      = "${AUTOREV}"


RDEPENDS_${PN} += " \
    qtmultimedia-qmlplugins \
    qtmultimedia-rtlfm-radio-plugin \
    qtquickcontrols-qmlplugins \
    qtquickcontrols2-qmlplugins \
    qtsvg-plugins \
    "

do_install_prepend() {
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
    zip controls.wgt config.xml controls



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
    zip dashboard.wgt config.xml dashboard



    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \
<widget xmlns=\"http://www.w3.org/ns/widgets\" id=\"home\" version=\"0.1\"> \
  <name>Home</name> \
  <content src=\"home\" type=\"application/x-executable\"/> \
  <description>Home app.</description> \
  <author>Qt</author> \
  <icon src=\"home.png\"/> \
  <license>Apache 2.0</license> \
</widget> \
" > ${B}/apps/Home/config.xml

    cd ${B}/apps/Home/
    zip home.wgt config.xml home



    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \
<widget xmlns=\"http://www.w3.org/ns/widgets\" id=\"hvac\" version=\"0.1\"> \
  <name>HVAC</name> \
  <content src=\"hvac\" type=\"application/x-executable\"/> \
  <description>HVAC app.</description> \
  <author>Qt</author> \
  <icon src=\"hvac.png\"/> \
  <license>Apache 2.0</license> \
</widget> \
" > ${B}/apps/HVAC/config.xml

    cd ${B}/apps/HVAC/
    zip hvac.wgt config.xml hvac



    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \
<widget xmlns=\"http://www.w3.org/ns/widgets\" id=\"mediaplayer\" version=\"0.1\"> \
  <name>MediaPlayer</name> \
  <content src=\"mediaplayer\" type=\"application/x-executable\"/> \
  <description>MediaPlayer app.</description> \
  <author>Qt</author> \
  <icon src=\"mediaplayer.png\"/> \
  <license>Apache 2.0</license> \
</widget> \
" > ${B}/apps/MediaPlayer/config.xml

    cd ${B}/apps/MediaPlayer/
    zip mediaplayer.wgt config.xml mediaplayer



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
    zip phone.wgt config.xml phone



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
    zip radio.wgt config.xml radio



    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \
<widget xmlns=\"http://www.w3.org/ns/widgets\" id=\"settings\" version=\"0.1\"> \
  <name>Settings</name> \
  <content src=\"settings\" type=\"application/x-executable\"/> \
  <description>Settings app.</description> \
  <author>Qt</author> \
  <icon src=\"settings.png\"/> \
  <license>Apache 2.0</license> \
</widget> \
" > ${B}/apps/Settings/config.xml

    cd ${B}/apps/Settings/
    zip settings.wgt config.xml settings


    cat > ${B}/apps/installAllApps.sh <<-EOF
	#!/bin/sh
	/usr/bin/afm-util install controls.wgt
	/usr/bin/afm-util install dashboard.wgt
	/usr/bin/afm-util install home.wgt
	/usr/bin/afm-util install hvac.wgt
	/usr/bin/afm-util install mediaplayer.wgt
	/usr/bin/afm-util install phone.wgt
	/usr/bin/afm-util install radio.wgt
	/usr/bin/afm-util install settings.wgt
	EOF
}

do_install() {
    install -d ${D}/usr/AGL/${PN}
    install -m 0644 ${B}/apps/Controls/controls.wgt ${D}/usr/AGL/${PN}/
    install -m 0644 ${B}/apps/Dashboard/dashboard.wgt ${D}/usr/AGL/${PN}/
    install -m 0644 ${B}/apps/Home/home.wgt ${D}/usr/AGL/${PN}/
    install -m 0644 ${B}/apps/HVAC/hvac.wgt ${D}/usr/AGL/${PN}/
    install -m 0644 ${B}/apps/MediaPlayer/mediaplayer.wgt ${D}/usr/AGL/${PN}/
    install -m 0644 ${B}/apps/Phone/phone.wgt ${D}/usr/AGL/${PN}/
    install -m 0644 ${B}/apps/Radio/radio.wgt ${D}/usr/AGL/${PN}/
    install -m 0644 ${B}/apps/Settings/settings.wgt ${D}/usr/AGL/${PN}/

    install -m 0755 ${B}/apps/installAllApps.sh ${D}/usr/AGL/${PN}/

    install -d ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/qmldir ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/ImageButton.qml ${D}${libdir}/qt5/qml/AGL/Demo/Controls/
    install -m 0644 ${S}/imports/AGL/Demo/Controls/ToggleButton.qml ${D}${libdir}/qt5/qml/AGL/Demo/Controls/

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
	/usr/lib/qt5/qml/AGL/Demo/Controls/qmldir \
	/usr/lib/qt5/qml/AGL/Demo/Controls/ImageButton.qml \
	/usr/lib/qt5/qml/AGL/Demo/Controls/ToggleButton.qml \
	/usr/lib/qt5/qml/QtQuick/Controls.2/AGL \
	/usr/lib/qt5/qml/QtQuick/Controls.2/AGL/images \
	"
