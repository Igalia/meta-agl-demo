DESCRIPTION = "Build VideoOnDemand server"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
#LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
#		   "  

PV = "0.1"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/vod-server;protocol=https;branch=${AGL_BRANCH}"
SRC_URI += "file://0001-Fix-cross-compilation-with-yocto.patch \
            file://vod-demo.service"

S = "${WORKDIR}/git"
SRCREV = "faafe61dd5f88859a005a59082d57339089ddca1"

do_install() {
        install -m 0755 -d ${D}${bindir}
        install -m 0755 ${S}/VideoOnDemand ${D}${bindir}/VideoOnDemand

        # Install VOD server systemd service (user)
        if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
            install -m 644 -p -D ${WORKDIR}/vod-demo.service ${D}${systemd_user_unitdir}/vod-demo.service

            # Execute install manually for root user on behalf of systemctl script
            # because it doesn't support user mode of systemd.
            install -m 0755 -d ${D}${ROOT_HOME}/.config/systemd/user/default.target.wants/
            ln -sf ${systemd_user_unitdir}/vod-demo.service ${D}${ROOT_HOME}/.config/systemd/user/default.target.wants/vod-demo.service
            sed -i "s:/home/root:${ROOT_HOME}:" ${D}${ROOT_HOME}/.config/systemd/user/default.target.wants/vod-demo.service
        fi
}

FILES_${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_user_unitdir}/vod-demo.service', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${ROOT_HOME}/.config/systemd/user/default.target.wants/vod-demo.service', '', d)} \
    "
