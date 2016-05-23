SUMMARY = "Execute Script QML plugin"
DESCRIPTION = "This provides a simple QML plugin able to execute script file."

LICENSE = "CLOSED"

DEPENDS = "qtbase-native qtdeclarative"

SRC_URI = "git://github.com/ntanibata/qml-execscript-plugin.git;protocol=git;"
SRCREV = "1f05c44cccd8aef485d8df8206c8df4e5ad6f310"
S = "${WORKDIR}/git"

inherit qmake5

EXTRA_OECONF = "--with-moc-dir=${STAGING_BINDIR_NATIVE}/qt5"
QML_LIBDIR = "${libdir}/qt5/qml"

do_install() {
    install -d ${D}${libdir}/qt5/qml/execScript/
    install -m 0755 execScript/*.so ${D}${libdir}/qt5/qml/execScript/
    install -m 0644 ../git/execScript/qmldir ${D}${libdir}/qt5/qml/execScript/
}

FILES_${PN} += "${QML_LIBDIR}/execScript/libexecscriptplugin.so"
FILES_${PN} += "${QML_LIBDIR}/execScript/qmldir"
FILES_${PN}-dbg += "${QML_LIBDIR}/execScript/.debug"
