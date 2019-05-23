SUMMARY     = "AGL Launcher Application"
DESCRIPTION = "AGL Launcher Application build with recipe method"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/apps/launcher"
SECTION     = "apps"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "\
        qtbase \
        qtdeclarative \
        qtquickcontrols2 \
        qtwebsockets \
        qlibwindowmanager \
        qlibhomescreen \
"

inherit qmake5 systemd pkgconfig aglwgt

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/launcher;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AGL_APP_REVISION}"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git/"

PATH_prepend = "${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_QT_BINS}:"
