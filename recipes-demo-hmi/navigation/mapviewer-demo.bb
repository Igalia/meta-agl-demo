SUMMARY     = "Setting files of mapviewer for the AGL Demonstrator"
DESCRIPTION = "Setting files of mapviewer for the AGL Demonstrator"
LICENSE     = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SECTION     = "apps"

inherit systemd

SRC_URI = " \
        file://weston-ready.conf \
        file://mapviewer-demo-network-conf.service \
"

do_install() {
    # Install cluster demo network configuration service unit
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/mapviewer-demo-network-conf.service ${D}${systemd_system_unitdir}

    # Add symlink to network.target.wants
    install -d ${D}${sysconfdir}/systemd/system/network.target.wants
    ln -s ${systemd_system_unitdir}/mapviewer-demo-network-conf.service ${D}${sysconfdir}/systemd/system/network.target.wants/

    # Workaround for now to ensure that the windowmanager and its dependencies
    # start after weston, which takes longer with gst-record enabled.
    # This should be investigated a bit further and likely reworked into
    # something more generically applicable.
    install -d ${D}${sysconfdir}/systemd/system/afm-api-windowmanager@.service.d
    install -m 0644 ${WORKDIR}/weston-ready.conf ${D}${sysconfdir}/systemd/system/afm-api-windowmanager@.service.d
}

FILES_${PN} += " \
	${sysconfdir}/xdg/weston/ \
	${systemd_system_unitdir} \
"
