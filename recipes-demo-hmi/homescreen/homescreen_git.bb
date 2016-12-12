SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application + SampleAppTimeDate + HomeScreenAppFrameworkBinderAGL + WindowManager + InputEventManager + two sample apps (QML and Qtwidget)"
HOMEPAGE    = "https://wiki.automotivelinux.org/homescreen"
LICENSE     = "Apache-2.0"
SECTION     = "apps"
S           = "${WORKDIR}/git/"

BBCLASSEXTEND = " nativesdk"

inherit qmake5 systemd pkgconfig
DEPENDS = " qtbase "

# for HomeScreenAppFrameworkBinderTizen:
#DEPENDS += " pkgmgr-info aul "
# for WindowManager:
DEPENDS += " wayland-ivi-extension "
# for libhomescreen
DEPENDS += " glib-2.0 "
# for sample apps
DEPENDS += " qtdeclarative qtquickcontrols2 "

LIC_FILES_CHKSUM = "file://HomeScreen/LICENSE;md5=ae6497158920d9524cf208c09cc4c984"
SRCREV  = "${AUTOREV}"
# PV needs to be modified with SRCPV to work AUTOREV correctly
PV = "0.0+git${SRCPV}"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/p/staging/HomeScreen.git;protocol=http"

PATH_prepend = "${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_QT_BINS}:"

do_install() {
    install -d ${D}/usr/AGL/${PN}
    install -m 0755 ${B}/HomeScreen/HomeScreen ${D}/usr/AGL/${PN}/
    install -m 0755 ${B}/SampleAppTimeDate/SampleAppTimeDate ${D}/usr/AGL/${PN}/
    install -m 0755 ${B}/SampleHomeScreenInterfaceApp/SampleHomeScreenInterfaceApp ${D}/usr/AGL/${PN}/
    install -m 0755 ${B}/HomeScreenAppFrameworkBinderAGL/HomeScreenAppFrameworkBinderAGL ${D}/usr/AGL/${PN}/
    install -m 0755 ${B}/WindowManager/WindowManager ${D}/usr/AGL/${PN}/
    install -m 0755 ${B}/InputEventManager/InputEventManager ${D}/usr/AGL/${PN}/
    install -m 0755 ${B}/SampleNavigationApp/SampleNavigationApp ${D}/usr/AGL/${PN}/
    install -m 0755 ${B}/SampleMediaApp/SampleMediaApp ${D}/usr/AGL/${PN}/
    
    install -d ${D}${libdir}
    install -m 0644 ${B}/libhomescreen/libhomescreen.so.1.0.0 ${D}${libdir}/
    ln -sf ${libdir}/libhomescreen.so.1.0.0 ${D}${libdir}/libhomescreen.so
    ln -sf ${libdir}/libhomescreen.so.1.0.0 ${D}${libdir}/libhomescreen.so.1
    ln -sf ${libdir}/libhomescreen.so.1.0.0 ${D}${libdir}/libhomescreen.so.1.0

# kooltux: still some problem with paths inside .pc file
# error at build time:
# ERROR: homescreen-git-r0 do_populate_sysroot: QA Issue: homescreen.pc failed sanity test (tmpdir) in path /xdt/build/tmp/work/cortexa15hf-neon-agl-linux-gnueabi/homescreen/git-r0/sysroot-destdir/usr/lib/pkgconfig [pkgconfig]
#
#    install -d ${D}${libdir}/pkgconfig
#    install -m 0644 ${B}/libhomescreen/pkgconfig/homescreen.pc ${D}${libdir}/pkgconfig/

	install -d ${D}${includedir}
	install -m 0644 ${S}/libhomescreen/include/libhomescreen.hpp ${D}${includedir}/

    install -d ${D}${systemd_user_unitdir}
    install -m 0644 ${S}/HomeScreen/conf/HomeScreen.service ${D}${systemd_user_unitdir}
    install -m 0644 ${S}/HomeScreenAppFrameworkBinderAGL/conf/HomeScreenAppFrameworkBinderAGL.service ${D}${systemd_user_unitdir}
    install -m 0644 ${S}/InputEventManager/conf/InputEventManager.service ${D}${systemd_user_unitdir}
    install -m 0644 ${S}/WindowManager/conf/WindowManager.service ${D}${systemd_user_unitdir}

    install -d ${D}${sysconfdir}/systemd/user/default.target.wants
    ln -sf ${systemd_user_unitdir}/WindowManager.service ${D}${sysconfdir}/systemd/user/default.target.wants
    ln -sf ${systemd_user_unitdir}/HomeScreen.service ${D}${sysconfdir}/systemd/user/default.target.wants
}

FILES_${PN} += "/usr/AGL/${PN}/ ${libdir} ${systemd_user_unitdir}"
FILES_${PN}-dbg += "/usr/AGL/${PN}/.debug"


PACKAGES =+ "libhomescreen libhomescreen-dev libhomescreen-dbg"

FILES_libhomescreen = "\
	${libdir}/libhomescreen.so.* \
"
FILES_libhomescreen-dev = "\
	${includedir}/libhomescreen.hpp \
	${libdir}/libhomescreen.so \
	${libdir}/pkgconfig/homescreen.pc \
"
FILES_libhomescreen-dbg = "\
	${libdir}/.debug/libhomescreen.so.* \
"
RDEPENDS_libhomescreen-dbg += "${PN}-dbg libhomescreen-dev"

RDEPENDS_${PN}-dev += "libhomescreen-dev"

