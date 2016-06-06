SUMMARY = "AM/FM Radio QML plugin (for RTL2832U hardware)"
DESCRIPTION = "This provides a simple QML plugin able to detect and use \
radio hardware, most notably RTL2832U chipsets (although it can easily \
be extended). Among other things, it provides generic APIs to display \
status, switch between AM/FM mode, choose frequency, mute sound..."
HOMEPAGE = "https://github.com/iotbzh/qml_radio_plugin"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
                    file://qml_radio.cpp;endline=16;md5=10a65c128e3a6cff2a61bb38556ceb04"

DEPENDS = "qtbase-native qtdeclarative rtl-sdr pulseaudio alsa-lib"
RDEPENDS_${PN}-examples = "qtdeclarative-tools qtquickcontrols-qmlplugins"

SRC_URI = "git://github.com/iotbzh/qml_radio_plugin"
SRCREV = "36e6bbaaa6637abab344d38d8a0c4794fa42fb03"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-moc-dir=${STAGING_BINDIR_NATIVE}/qt5"

QML_LIBDIR = "${libdir}/qt5/qml"

do_configure_append() {
     # when building with "security_flags.inc", libtool thinks that
     # "-pie" means we want an executable, and links with related C
     # runtime objects. Work around this until libtool is fixed.
     sed -i 's/".\+Scrt1.o/"/g' ${B}/*libtool
     sed -i 's/ .\+Scrt1.o/ /g' ${B}/*libtool
     sed -i 's/-lgcc / /g' ${B}/*libtool
     sed -i 's/-lgcc"/"/g' ${B}/*libtool
}

do_install_append() {
     # Remove .la files for loadable module
     rm -f ${D}/${QML_LIBDIR}/radio/*.la
     # Install QML example
     install -d ${D}/${datadir}/qt5/examples/radio
     install -m 0644 ${S}/radio.qml ${D}/${datadir}/qt5/examples/radio
}

PACKAGES += "${PN}-examples"

FILES_${PN} += "${QML_LIBDIR}/radio/*.so ${QML_LIBDIR}/radio/qmldir"
FILES_${PN}-dbg += "${QML_LIBDIR}/radio/.debug"
FILES_${PN}-examples += "${datadir}"
