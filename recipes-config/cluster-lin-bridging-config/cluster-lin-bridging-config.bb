DESCRIPTION = "Configure LIN to external CAN bridging"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "\
    file://cluster-lin-bridging.service \
"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "cluster-lin-bridging.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/cluster-lin-bridging.service ${D}${systemd_system_unitdir}
}

FILES_${PN} += "${systemd_system_unitdir}"

RDEPENDS_${PN} = " \
	can-utils \
	sllin \
	sllin-virtual \
"
