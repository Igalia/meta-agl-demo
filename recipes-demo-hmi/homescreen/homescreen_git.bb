SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application build with recipe method"
HOMEPAGE    = "http://docs.automotivelinux.org"
LICENSE     = "Apache-2.0"
SECTION     = "apps"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

DEPENDS = "\
        qtbase \
        qtdeclarative \
        qtquickcontrols2 \
        agl-service-homescreen \
        agl-service-weather \
        libqtappfw \
        libhomescreen \
        libafb-helpers-qt \
        wayland-native \
        wayland \
        qtwayland \
        qtwayland-native \
"

inherit qmake5 systemd pkgconfig aglwgt

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/homescreen;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AGL_APP_REVISION}"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git/"

PATH_prepend = "${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_QT_BINS}:"

OE_QMAKE_CXXFLAGS_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', '' , '-DQT_NO_DEBUG_OUTPUT', d)}"

RDEPENDS_${PN} += " \
        libqtappfw \
"
