DESCRIPTION = "systemd service for slLIN demo"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit systemd

SRC_URI_append = " \
	file://sllin-demo.service \
	file://start_lin_demo.sh \
	file://lin_config.conf \
"

SYSTEMD_SERVICE_${PN} = "sllin-demo.service"

do_install_append () {
	install -d 644 ${D}/${bindir}
	install -m 755 ${WORKDIR}/start_lin_demo.sh ${D}/${bindir}/start_lin_demo.sh
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/sllin-demo.service ${D}${systemd_system_unitdir}/
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/lin_config.conf ${D}${sysconfdir}/
}

FILES_${PN} += "${bindir}/start_lin_demo.sh ${sysconfdir}/lin_config.conf"

RDEPENDS_${PN} += "lin-config"
