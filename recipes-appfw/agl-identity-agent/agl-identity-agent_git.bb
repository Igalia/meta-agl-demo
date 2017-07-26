DESCRIPTION = "AGL identity agent binding"
HOMEPAGE = "https://gitlab.com/iotbzh/aia-binding"
SECTION = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/iotbzh/aia-binding.git;protocol=https;branch=master \
           file://main.conf"
SRCREV  = "0bbf447e05691d6d14bae65bdbcfaef3c668bb1a"

inherit cmake systemd

S = "${WORKDIR}/git"

DEPENDS = "curl af-binder bluez5 json-c systemd"

do_install_append () {
    install -d ${D}${sysconfdir}/bluetooth
    install -m 0644 ${WORKDIR}/main.conf ${D}${sysconfdir}/bluetooth/main.conf

    install -d ${D}${systemd_user_unitdir}
    install -m 0644 ${B}/agl-identity-agent.service ${D}${systemd_user_unitdir}

    install -d ${D}${sysconfdir}/systemd/user/default.target.wants
    ln -sf ${systemd_user_unitdir}/agl-identity-agent.service ${D}${sysconfdir}/systemd/user/default.target.wants
}

FILES_${PN} += "${systemd_user_unitdir}"

