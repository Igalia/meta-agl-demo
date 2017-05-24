SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application + SampleAppTimeDate"
HOMEPAGE    = "http://docs.automotivelinux.org"
LICENSE     = "Apache-2.0"
SECTION     = "apps"
S           = "${WORKDIR}/git/"

inherit qmake5 systemd pkgconfig
DEPENDS = " qtbase qtdeclarative qtquickcontrols2 pulseaudio"
RDEPENDS_${PN} = " \
	homescreenappframeworkbinderagl \
	inputeventmanager \
	windowmanager"

LIC_FILES_CHKSUM = "file://homescreen/LICENSE;md5=ae6497158920d9524cf208c09cc4c984"
SRCREV  = "${AUTOREV}"
# PV needs to be modified with SRCPV to work AUTOREV correctly
PV = "0.0+git${SRCPV}"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/p/apps/homescreen.git;protocol=http \
           file://dbus-homescreen.conf.in"

PATH_prepend = "${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_QT_BINS}:"

do_install() {
    install -d ${D}/usr/AGL/${PN}
    install -m 0755 ${B}/homescreen/HomeScreen ${D}/usr/AGL/${PN}/
    install -m 0755 ${B}/sampleapptimedate/SampleAppTimeDate ${D}/usr/AGL/${PN}/

# claneys: add dbus policy to make wifi/bluetooth status icon working as quick 
# workaround. (jira.automotivelinux.org : SPEC-377)
    install -d ${D}/etc/dbus-1/session.d
    install -m 0644 ${WORKDIR}/dbus-homescreen.conf.in ${D}/etc/dbus-1/session.d/homescreen.conf

    install -d ${D}${systemd_user_unitdir}
    install -m 0644 ${S}/homescreen/conf/HomeScreen.service ${D}${systemd_user_unitdir}

    install -d ${D}${sysconfdir}/systemd/user/default.target.wants
    ln -sf ${systemd_user_unitdir}/HomeScreen.service ${D}${sysconfdir}/systemd/user/default.target.wants
}

FILES_${PN} += "/usr/AGL/${PN}/ ${systemd_user_unitdir}"
FILES_${PN}-dbg += "/usr/AGL/${PN}/.debug"

