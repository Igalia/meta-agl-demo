LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE-2.0.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS += "qtbase qtdeclarative qtquickcontrols2 qlibwindowmanager virtual/libhomescreen"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/qtaglextras;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AGL_DEFAULT_REVISION}"

PV = "5.8.0+git${SRCPV}"
S = "${WORKDIR}/git/"

inherit qmake5

PACKAGES += "${PN}-mkspecs"

FILES_${PN}-mkspecs = "\
    ${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs \
"

FILES_${PN}-dev += " \
    ${OE_QMAKE_PATH_LIBS}/lib*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_LIBS}/pkgconfig \
    ${OE_QMAKE_PATH_LIBS}/cmake/* \
    ${OE_QMAKE_PATH_LIBS}/*.prl \
    ${OE_QMAKE_PATH_LIBS}/*.la \
    ${OE_QMAKE_PATH_DATA}/* \
    ${OE_QMAKE_PATH_HEADERS}/* \
"
