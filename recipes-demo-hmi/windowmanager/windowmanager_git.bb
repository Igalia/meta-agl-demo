SUMMARY     = "AGL WindowManager Application"
DESCRIPTION = "WindowManager"
HOMEPAGE    = "http://docs.automotivelinux.org"
LICENSE     = "Apache-2.0"
SECTION     = "apps"
S           = "${WORKDIR}/git/"

inherit qmake5 systemd
DEPENDS = " qtbase "

# for WindowManager:
DEPENDS += " wayland-ivi-extension "

LIC_FILES_CHKSUM = "file://windowmanager/LICENSE;md5=ae6497158920d9524cf208c09cc4c984"
SRCREV  = "${AGL_DEFAULT_REVISION}"
# PV needs to be modified with SRCPV to work AUTOREV correctly
PV = "0.0+git${SRCPV}"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/p/src/windowmanager.git;protocol=https;branch=${AGL_BRANCH}"

PATH_prepend = "${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_QT_BINS}:"

do_install() {
    install -d ${D}/usr/AGL/${PN}
    install -m 0755 ${B}/windowmanager/WindowManager ${D}/usr/AGL/${PN}/
    
    install -d ${D}${systemd_user_unitdir}
    install -m 0644 ${S}/windowmanager/conf/WindowManager.service ${D}${systemd_user_unitdir}
    install -m 0644 ${S}/windowmanager/conf/WindowManager.path ${D}${systemd_user_unitdir}

    install -d ${D}${sysconfdir}/systemd/user/default.target.wants
    ln -sf ${systemd_user_unitdir}/WindowManager.path ${D}${sysconfdir}/systemd/user/default.target.wants
}

FILES_${PN} += "/usr/AGL/${PN}/ ${systemd_user_unitdir}"
FILES_${PN}-dbg += "/usr/AGL/${PN}/.debug"

