DESCRIPTION = "Simulate can messages of a driving car"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "\
    file://cluster-demo-simulator.service \
    file://simple_can_simulator.py \
"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "cluster-demo-simulator.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/cluster-demo-simulator.service ${D}${systemd_system_unitdir}
    install -d ${D}${sbindir}
    install -m 0755 ${WORKDIR}/simple_can_simulator.py ${D}${sbindir}
}

FILES_${PN} += "${systemd_system_unitdir}"

RDEPENDS_${PN} = " \
	can-utils \
	python3 \
"
