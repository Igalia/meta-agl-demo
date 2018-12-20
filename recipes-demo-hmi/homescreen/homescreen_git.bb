SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application build with recipe method"
HOMEPAGE    = "http://docs.automotivelinux.org"
LICENSE     = "Apache-2.0"
SECTION     = "apps"
LIC_FILES_CHKSUM = "file://homescreen/LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

DEPENDS = "\
        qtbase \
        qtdeclarative \
        qtquickcontrols2 \
        pulseaudio \
        agl-service-homescreen \
        agl-service-windowmanager \
        agl-service-weather \
        libqtappfw \
        qlibwindowmanager \
        libhomescreen \
"

inherit qmake5 systemd pkgconfig aglwgt

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/apps/homescreen;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AGL_APP_REVISION}"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git/"

PATH_prepend = "${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_QT_BINS}:"
