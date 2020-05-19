SUMMARY = "Qt QR code library"
DESCRIPTION = "Qt/C++ library for encoding and visualization of data in a \
QR Code symbol. This library consists of a Qt wrapper for libqrencode, and \
Qt components that are able to visualize the result."
LICENSE = "LGPLv3+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94a3f3bdf61243b5e5cf569fbfbbea52"

DEPENDS = "qtbase qtdeclarative qtquickcontrols2 qtsvg qrencode"

SRC_URI = "git://github.com/danielsanfr/qt-qrcode.git;protocol=https \
           file://0001-rework-library-build.patch \
           file://0002-fix-dangling-pointer.patch \
           "
SRCREV = "2d57d9c6e2341689d10f9360a16a08831a4a820b"

PV = "git${SRCPV}"
S = "${WORKDIR}/git/"

inherit qmake5
