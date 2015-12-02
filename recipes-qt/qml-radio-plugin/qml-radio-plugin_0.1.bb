SUMMARY = "AM/FM Radio QML plugin (for RTL2832U hardware)"
DESCRIPTION = "This provides a simple QML plugin able to detect and use radio hardware, most notable RTL2832U chipsets (although it can easily be extended). Among other things, it provides generic APIs to display status, switch between AM/FM mode, choose frequency, mute sound..."
HOMEPAGE = "https://github.com/iotbzh/qml_radio_plugin"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
                    file://qml_radio.cpp;endline=16;md5=10a65c128e3a6cff2a61bb38556ceb04"

DEPENDS = "qtbase-native qtdeclarative rtl-sdr alsa-lib"
RDEPENDS_${PN} = "qtdeclarative-tools qtquickcontrols-qmlplugins"

SRC_URI = "git://github.com/iotbzh/qml_radio_plugin"
SRCREV = "0032dc92d1a208219a0f782b68aef7f4cbcc0028"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-moc-dir=${STAGING_BINDIR_NATIVE}/qt5"

QML_LIBDIR = "${libdir}/qt5/qml"

do_install_append() {
     # Remove .la files for loadable modules
     rm -f ${D}/${QML_LIBDIR}/radio/*.la
     # Install QML example
     install -d ${D}/${datadir}/qt5/examples/radio
     install -m 0644 ${S}/radio.qml ${D}/${datadir}/qt5/examples/radio
}

FILES_${PN} += "${QML_LIBDIR}/radio/*.so ${QML_LIBDIR}/radio/qmldir ${datadir}"
FILES_${PN}-dbg += "${QML_LIBDIR}/radio/.debug"
