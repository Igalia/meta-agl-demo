SUMMARY     = "HVAC for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating HVAC on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/hvac"
LICENSE     = "Apache-2.0"
SECTION     = "apps"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit qmake5

# 'wgtpkg-pack' in af-main-native is required.
DEPENDS = "af-main-native"

LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/hvac;protocol=http"
SRCREV  = "${AUTOREV}"

RDEPENDS_${PN} += " \
    qtmultimedia-qmlplugins \
    qtquickcontrols2-qmlplugins \
    "

do_install() {
    install -d ${D}/usr/AGL/apps
    install -m 0644 ${B}/package/${PN}.wgt ${D}/usr/AGL/apps/
}

FILES_${PN} += "/usr/AGL/apps/ \
    /usr/AGL/apps/${PN} \
    "
