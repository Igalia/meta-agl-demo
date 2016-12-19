FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = "\
    file://mapviewer.service \
    "

inherit systemd

SYSTEMD_PACKAGES = "${PN}"

do_install_append() {
    # Install systemd unit files
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -m 644 -p -D ${WORKDIR}/mapviewer.service ${D}${systemd_system_unitdir}/mapviewer.service
    fi
}

FILES_${PN} += " \
    ${systemd_system_unitdir}/mapviewer.service \
    "
