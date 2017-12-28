SUMMARY     = "Radio Service Binding"
DESCRIPTION = "AGL Radio Service Binding"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/agl-service-radio"
SECTION     = "apps"


LICENSE     = "Apache-2.0 & GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984 \
                    file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/apps/agl-service-radio;protocol=https;branch=${AGL_BRANCH} \
           file://set-4a-output-sink.conf \
"
SRCREV  = "62518e9fdefa82a8d454e3dbcc28fd2efdc08fa0"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS = "rtl-sdr glib-2.0 pulseaudio alsa-lib"

inherit cmake aglwgt pkgconfig

EXTRA_OECMAKE_append_m3ulcb = " -DHAVE_KINGFISHER=1"

do_install_append() {
	if ${@bb.utils.contains('DISTRO_FEATURES', 'agl-audio-4a-framework', 'true', 'false', d)}; then
		install -d ${D}${systemd_user_unitdir}/afm-service-agl-service-radio@1.0.service.d
		install -m 0644 ${WORKDIR}/set-4a-output-sink.conf ${D}${systemd_user_unitdir}/afm-service-agl-service-radio@1.0.service.d/
	fi
}

FILES_${PN} += "${systemd_user_unitdir}"
