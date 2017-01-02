DESCRIPTION = "Build NetworkManager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
#LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
#		   "  

PV = "0.1"

DEPENDS += "libxml2"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/unicens;protocol=https"
SRC_URI_chinook = "git://gerrit.automotivelinux.org/gerrit/src/unicens;protocol=https;branch=chinook"
SRC_URI_append = " file://0001-Fix-cross-compilation-with-yocto.patch"
SRC_URI_append = " file://0001-Support-booting-by-systemd.patch \
            file://most-network-startup.service \
            file://most-network-manager.service"

S = "${WORKDIR}/git"
#SRCREV = "8c5f2324d7aa61669324aec1a0ad091fe1379489"
SRCREV = "${AUTOREV}"
SRCREV_chinook = "7d889414539b8722e0093f3997ccbf2008c0e189"

do_install() {
    install -m 0755 -d ${D}/usr/AGL/most
    install -m 0755 ${S}/NetworkManager ${D}/usr/AGL/most/MostNetworkManager
    install -m 0644 ${S}/scripts/config-agl.xml ${D}/usr/AGL/most/
    install -m 0644 ${S}/scripts/camera-os88122-ts.script ${D}/usr/AGL/most/
    install -m 0644 ${S}/scripts/i2c-slim-amplifier-v2.3.script ${D}/usr/AGL/most/
    install -m 0644 ${S}/scripts/i2c-uda1388-v2.3.script ${D}/usr/AGL/most
    install -m 0755 ${S}/scripts/loadDriver.sh ${D}/usr/AGL/most

    # Install MOST network startup systemd service (user)
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -m 644 -p -D ${WORKDIR}/most-network-startup.service ${D}${systemd_user_unitdir}/most-network-startup.service
        install -m 644 -p -D ${WORKDIR}/most-network-manager.service ${D}${systemd_user_unitdir}/most-network-manager.service

        # Execute install manually for root user on behalf of systemctl script
        # because it doesn't support user mode of systemd.
        install -m 0755 -d ${D}/home/root/.config/systemd/user/default.target.wants/
        ln -sf ${systemd_user_unitdir}/most-network-startup.service ${D}/home/root/.config/systemd/user/default.target.wants/most-network-startup.service
        ln -sf ${systemd_user_unitdir}/most-network-manager.service ${D}/home/root/.config/systemd/user/default.target.wants/most-network-manager.service
    fi
}

FILES_${PN} += " \
    /usr/AGL/most \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_user_unitdir}/most-network-startup.service', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_user_unitdir}/most-network-manager.service', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '/home/root/.config/systemd/user/default.target.wants/most-network-startup.service', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '/home/root/.config/systemd/user/default.target.wants/most-network-manager.service', '', d)} \
    "
