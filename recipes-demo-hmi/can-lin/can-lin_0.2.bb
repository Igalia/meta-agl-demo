DESCRIPTION = "CAN-LIN Application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/staging/most-can-demo-bin;protocol=https"
SRC_URI += "file://hvac-demo.service"

S = "${WORKDIR}/git"
SRCREV = "fd2101e277cb80eef4c6381924cfdc1d50087d12"

# These binaries are currently for the porter kernel only
COMPATIBLE_MACHINE = "porter"

do_install() {
        install -m 0755 -d ${D}${bindir}
        install -m 0755 ${S}/OptoLyzerMoccaApp ${D}${bindir}
        # Hack for vod service for RSE with most/hvac demo hardware

        # vod-client is provided as binary stored and delivered in staging/most-can-demo-bin.
        install -m 0755 -d ${D}/usr/AGL/most
        install -m 0755 ${S}/vod-client ${D}/usr/AGL/most

        # Install HVAC DEMO hardware startup systemd service (user)
        if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
            install -m 644 -p -D ${WORKDIR}/hvac-demo.service ${D}${systemd_user_unitdir}/hvac-demo.service
        fi

        # Execute install manually for root user on behalf of systemctl script
        # because it doesn't support user mode of systemd.
        install -m 0755 -d ${D}/home/root/.config/systemd/user/default.target.wants/
        ln -sf ${systemd_user_unitdir}/hvac-demo.service ${D}/home/root/.config/systemd/user/default.target.wants/hvac-demo.service
}

FILES_${PN} += " \
    ${bindir}/OptoLyzerMoccaApp \
    /usr/AGL/most/vod-client \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_user_unitdir}/hvac-demo.service', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '/home/root/.config/systemd/user/default.target.wants/hvac-demo.service', '', d)} \
    "
