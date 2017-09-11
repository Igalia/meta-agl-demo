FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = "\
    file://mapviewer-settings.sh \
    file://mapviewer.service \
    "

inherit systemd

SYSTEMD_PACKAGES = "${PN}"

do_install_append() {
    install -d ${D}/usr/AGL/${PN}
    install -m 0755 ${WORKDIR}/mapviewer-settings.sh ${D}/usr/AGL/${PN}/

    # Install systemd unit files
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_user_unitdir}
        install -m 644 -p -D ${WORKDIR}/mapviewer.service ${D}${systemd_user_unitdir}/mapviewer.service
    fi
}

## DO NOT ENABLE 'weston-mapviewer-demo.service' BY DEFAULT
##
## This should be enabled/disabled by 'switch_on_mapviewer-demo.sh'/'switch_off_mapviewer-demo.sh'.
##
#SYSTEMD_SERVICE_${PN} = "mapviewer.service"

FILES_${PN} += " \
    ${systemd_user_unitdir}/mapviewer.service \
    /usr/AGL/mapviewer/ \
    "
