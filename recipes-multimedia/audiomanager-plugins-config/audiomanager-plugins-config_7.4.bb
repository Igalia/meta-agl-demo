DESCRIPTION = "Configurations for Audiomanager Plugins for AGL Demo"
HOMEPAGE = ""
LICENSE = "MPLv2"
SECTION = "multimedia"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"
RDEPENDS_${PN} = "libxml2 zlib dbus-lib"

SRC_URI = " \
    file://amcp_dbus.conf \
    file://amrp_dbus.conf \
    file://configuration.xml \
    file://customtypes.xsd \
    file://org.genivi.audiomanager.routing.pulseaudio.conf \
"

FILES_${PN} = " \
    ${sysconfdir}/dbus-1/system.d/amcp_dbus.conf \
    ${sysconfdir}/dbus-1/system.d/amrp_dbus.conf \
    ${sysconfdir}/dbus-1/system.d/org.genivi.audiomanager.routing.pulseaudio.conf \
    ${sysconfdir}/audiomanager/control/configuration.xml \
    ${sysconfdir}/audiomanager/control/customtypes.xsd \
"

FILES_${PN}-dbg += " \
    /usr/lib/audiomanager/command/.debug \
    /usr/lib/audiomanager/routing/.debug \
"

do_install() {
    install -d ${D}${sysconfdir}/dbus-1/system.d
    install -m 644 ${WORKDIR}/amcp_dbus.conf ${D}${sysconfdir}/dbus-1/system.d/
    install -m 644 ${WORKDIR}/amrp_dbus.conf ${D}${sysconfdir}/dbus-1/system.d/
    install -m 644 ${WORKDIR}/org.genivi.audiomanager.routing.pulseaudio.conf ${D}${sysconfdir}/dbus-1/system.d/
    install -d ${D}${sysconfdir}/audiomanager/control
    install -m 644 ${WORKDIR}/configuration.xml ${D}${sysconfdir}/audiomanager/control/
    install -m 644 ${WORKDIR}/customtypes.xsd ${D}${sysconfdir}/audiomanager/control/
}

RPROVIDES_${PN} = "virtual/audiomanager-plugins-config"
