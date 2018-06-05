inherit allarch

SUMMARY = "Provides the 'web-runtime' command"
DESCRIPTION = "The command 'web-runtime' is an abstraction that allows to "

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "\
	file://web-runtime;md5sum=2245be1e6029b30966f0842e3fee75ea \
	file://web-runtime.qml;md5sum=922aeae6d596d7c83af01abca266f0df \
	file://web-runtime-webkit.qml;md5sum=4daf9df39078634c27a7923d37e82e3d \
"

RDEPENDS_${PN} = "\
  qtdeclarative-tools \
  qtwayland-qmlplugins \
  qtquickcontrols-qmlplugins \
"

PROVIDES += "virtual/webruntime"
# add also RPROVIDES to satisfy the packagegroup
RPROVIDES_${PN} += "virtual/webruntime"

do_configure() {
    :
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/web-runtime ${D}${bindir}/web-runtime
    install -m 0644 ${WORKDIR}/web-runtime.qml ${D}${bindir}/web-runtime.qml
    install -m 0644 ${WORKDIR}/web-runtime-webkit.qml ${D}${bindir}/web-runtime-webkit.qml
}

do_install_append_rcar-gen2() {
	# workaround for porter board: force the use of libEGL provided by mesa at runtime
	# otherwise, the proprietary libEGL is used and a problem then occurs due to a missing EGL function
    sed -i 's|^\(exec /usr/bin/qt5/qmlscene\)|LD_PRELOAD=/usr/lib/libEGL.so \1|g' ${D}${bindir}/web-runtime
}


