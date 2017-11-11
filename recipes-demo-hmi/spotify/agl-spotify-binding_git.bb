DESCRIPTION = "AGL low level user database binding"
HOMEPAGE = "https://github.com/iotbzh/agl-spotify-binding"
SECTION = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "gitsm://github.com/iotbzh/agl-spotify-binding.git;protocol=https;branch=master"
SRCREV  = "${AUTOREV}"

inherit cmake aglwgt pkgconfig

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"

DEPENDS += " af-binder json-c systemd curl "

do_install() {
	install -d ${D}/usr/libexec/spotify/
	install -m 0755 ${S}/src/playspot ${D}/usr/libexec/spotify/playspot
}

do_install_append_m3ulcb() {
	install -m 0755 ${S}/librespot-bin/librespot_m3ulcb ${D}/usr/libexec/spotify/librespot
	install -m 0755 ${S}/librespot-bin/README ${D}/usr/libexec/spotify/README
	install -m 0755 ${S}/librespot-bin/LICENSE ${D}/usr/libexec/spotify/LICENSE
}
