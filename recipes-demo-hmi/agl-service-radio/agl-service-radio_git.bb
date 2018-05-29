SUMMARY     = "Radio Service Binding"
DESCRIPTION = "AGL Radio Service Binding"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/agl-service-radio"
SECTION     = "apps"
LICENSE     = "Apache-2.0 & GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984 \
                    file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

PV = "1.0+git${SRCPV}"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/apps/agl-service-radio;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AGL_APP_REVISION}"

S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS = "rtl-sdr glib-2.0 gstreamer1.0"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'agl-audio-4a-framework', '' , 'pulseaudio', d)}"

inherit cmake aglwgt pkgconfig

EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES', 'agl-audio-4a-framework', '-DHAVE_4A_FRAMEWORK=1' , '', d)}"
