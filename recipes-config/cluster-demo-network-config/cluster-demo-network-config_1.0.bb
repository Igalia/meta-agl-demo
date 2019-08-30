SUMMARY     = "Setting files for cluster network for the AGL Demonstrator"
DESCRIPTION = "Setting files for cluster network for the AGL Demonstrator"
LICENSE     = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd allarch

SRC_URI = "file://cluster-demo-network-conf.service"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    # Install cluster demo network configuration service unit
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/cluster-demo-network-conf.service ${D}${systemd_system_unitdir}

    # Add symlink to network.target.wants
    install -d ${D}${sysconfdir}/systemd/system/network.target.wants
    ln -s ${systemd_system_unitdir}/cluster-demo-network-conf.service ${D}${sysconfdir}/systemd/system/network.target.wants/
}

FILES_${PN} += "${systemd_system_unitdir}"
