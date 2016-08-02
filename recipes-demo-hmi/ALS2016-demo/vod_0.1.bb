DESCRIPTION = "Video on demand"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PR = "r0"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/staging/most-can-demo-bin;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "cd6e46d117934139efdf5fac33fbeb59b66e45b6"

do_configure() {
        echo ""
}

do_compile() {
        echo ""
}

do_install() {
        install -m 0755 -d ${D}${bindir} ${D}${base_dir}/home/root ${D}${docdir}/video-on-demand
        install -m 0755 ${S}/NetworkManager ${D}${bindir}
        install -m 0755 ${S}/VideoOnDemand ${D}${bindir}
        install -m 0755 ${S}/vod-client ${D}${bindir}
	install -m 0644 ${S}/config-agl.xml ${D}${base_dir}/home/root
	install -m 0644 ${S}/i2c-slim-amplifier.script ${D}${base_dir}/home/root
	install -m 0644 ${S}/i2c-uda1388-v2.script ${D}${base_dir}/home/root
#        install -m 0644 ${WORKDIR}/README.txt ${D}${docdir}/video-on-demand
}
FILES_${PN} += "/home/root*"

