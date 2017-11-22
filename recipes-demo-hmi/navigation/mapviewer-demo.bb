SUMMARY     = "Setting files of mapviewer for the AGL Demonstrator @ CES2017"
DESCRIPTION = "Setting files of mapviewer for the AGL Demonstrator @ CES2017"
LICENSE     = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SECTION     = "apps"

inherit systemd

SRC_URI = " \
        file://switch_off_mapviewer-demo.sh \
        file://switch_on_mapviewer-demo.sh \
        file://weston-mapviewer-demo.ini \
        file://weston-mapviewer-demo.service \
        "

do_install() {
    # Map viewer demo
    install -d ${D}/usr/AGL/${PN}
    install -m 0755 ${WORKDIR}/switch_off_${PN}.sh ${D}/usr/AGL/${PN}
    install -m 0755 ${WORKDIR}/switch_on_${PN}.sh ${D}/usr/AGL/${PN}

    install -d ${D}${sysconfdir}/xdg/weston
    install -m 0644 ${WORKDIR}/weston-${PN}.ini ${D}${sysconfdir}/xdg/weston/weston-${PN}.ini

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/weston-mapviewer-demo.service ${D}${systemd_system_unitdir}
    sed -i "s:/home/root:${ROOT_HOME}:" ${D}${systemd_system_unitdir}/weston-mapviewer-demo.service
}

## DO NOT ENABLE 'weston-mapviewer-demo.service' BY DEFAULT
##
## The 'weston-mapviewer-demo.service' is exclusive of default 'weston.ini',
## it should be enabled/disabled by 'switch_on_mapviewer-demo.sh'/'switch_off_mapviewer-demo.sh'.
##
#SYSTEMD_SERVICE_${PN} = "weston-mapviewer-demo.service"

FILES_${PN} += " \
    ${systemd_system_unitdir} \
    /usr/AGL/${PN}/ \
    ${sysconfdir}/xdg/weston/${PN} \
    "
