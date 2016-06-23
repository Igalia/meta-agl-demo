DESCRIPTION = "MOST Linux Driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PR = "r0"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/staging/most-can-demo-bin;protocol=https"

COMPATIBLE_MACHINE = 'porter'

S = "${WORKDIR}/git"
SRCREV = "cd6e46d117934139efdf5fac33fbeb59b66e45b6"

do_configure() {
        echo ""
}

do_compile() {
        echo ""
}

do_install() {
        install -m 0755 -d ${D}${base_libdir}/modules/3.10.31-ltsi/kernel/drivers/${PN}
        install -m 0644 ${S}/mostcore.ko ${D}${base_libdir}/modules/3.10.31-ltsi/kernel/drivers/${PN}
        install -m 0644 ${S}/aim_cdev.ko ${D}${base_libdir}/modules/3.10.31-ltsi/kernel/drivers/${PN}
        install -m 0644 ${S}/aim_network.ko ${D}${base_libdir}/modules/3.10.31-ltsi/kernel/drivers/${PN}
        install -m 0644 ${S}/aim_sound.ko ${D}${base_libdir}/modules/3.10.31-ltsi/kernel/drivers/${PN}
        install -m 0644 ${S}/aim_v4l2.ko ${D}${base_libdir}/modules/3.10.31-ltsi/kernel/drivers/${PN}
        install -m 0644 ${S}/hdm_usb.ko ${D}${base_libdir}/modules/3.10.31-ltsi/kernel/drivers/${PN}
}

FILES_${PN} += "${base_libdir}/modules/3.10.31-ltsi/kernel/drivers/${PN}"

