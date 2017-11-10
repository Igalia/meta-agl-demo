DESCRIPTION = "AGL identity agent binding"
HOMEPAGE = "https://gitlab.com/iotbzh/aia-binding"
SECTION = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "gitsm://github.com/iotbzh/aia-binding.git;protocol=https;branch=master-next \
           file://main.conf"
SRCREV  = "2ffcc61a750a2bf4598662b4612283fdc9d2a4e4"

inherit cmake systemd

S = "${WORKDIR}/git"

DEPENDS = "curl af-binder json-c systemd"

do_install_append () {
    install -d ${D}${systemd_user_unitdir}
    install -m 0644 ${B}/agl-identity-agent.service ${D}${systemd_user_unitdir}

    install -d ${D}${sysconfdir}/systemd/user/default.target.wants
    ln -sf ${systemd_user_unitdir}/agl-identity-agent.service ${D}${sysconfdir}/systemd/user/default.target.wants
}

FILES_${PN} += "${systemd_user_unitdir}"

