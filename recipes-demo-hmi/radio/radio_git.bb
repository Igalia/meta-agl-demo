SUMMARY     = "Radio for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating Radio on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/radio"
SECTION     = "apps"

LICENSE     = "Apache-2.0 & GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/radio;protocol=https;branch=${AGL_BRANCH} \
           file://presets-ALS.conf \
           file://presets-CES.conf \
           file://presets-FOSDEM.conf \
"
SRCREV  = "${AGL_APP_REVISION}"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS = "qtquickcontrols2 rtl-sdr"
DEPENDS += "libhomescreen libqtappfw"

inherit qmake5 aglwgt

# ALS, CES, FOSDEM available
AGL_RADIO_PRESETS_LOCALE ?= "CES"
do_install_append() {
    install -d ${D}${sysconfdir}/xdg/AGL
    install -m 0644 ${WORKDIR}/presets-CES.conf ${D}${sysconfdir}/xdg/AGL/radio-presets-CES.conf
    install -m 0644 ${WORKDIR}/presets-ALS.conf ${D}${sysconfdir}/xdg/AGL/radio-presets-ALS.conf
    install -m 0644 ${WORKDIR}/presets-FOSDEM.conf ${D}${sysconfdir}/xdg/AGL/radio-presets-FOSDEM.conf
    install -m 0644 ${WORKDIR}/presets-${AGL_RADIO_PRESETS_LOCALE}.conf ${D}${sysconfdir}/xdg/AGL/radio-presets.conf
}

FILES_${PN} += "${sysconfdir}/xdg/AGL/*"

RDEPENDS_${PN} += "agl-service-radio libqtappfw"
