FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = "\
    file://mapviewer-settings.sh \
    file://mapviewer.service \
"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"

do_install_append() {
    install -d ${D}${prefix}/AGL/${PN}
    install -m 0755 ${WORKDIR}/mapviewer-settings.sh ${D}${prefix}/AGL/${PN}/

    # Install systemd unit file
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 644 ${WORKDIR}/mapviewer.service ${D}${systemd_system_unitdir}/mapviewer.service
    fi
}

SYSTEMD_SERVICE_${PN} = "mapviewer.service"

FILES_${PN} += "${prefix}/AGL/${PN}/"
