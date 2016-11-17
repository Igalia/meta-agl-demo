SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application + SampleAppTimeDate + HomeScreenAppFrameworkBinderAGL + WindowManager + InputEventManager + two sample apps (QML and Qtwidget)"
HOMEPAGE    = "https://wiki.automotivelinux.org/homescreen"
LICENSE     = "Apache-2.0"
SECTION     = "apps"
PV          = "0.6.2+gitr${SRCPV}"
S           = "${WORKDIR}/git/"

inherit qmake5 systemd
DEPENDS = " qtbase "

# for HomeScreenAppFrameworkBinderTizen:
#DEPENDS += " pkgmgr-info aul "
# for WindowManager:
DEPENDS += " wayland-ivi-extension "
# for libhomescreen
DEPENDS += " glib-2.0 "

LIC_FILES_CHKSUM = "file://HomeScreen/LICENSE;md5=ae6497158920d9524cf208c09cc4c984"
SRCREV  = "7f06418646c8822452f8541386810208c523f990"
SRC_URI = "git://gerrit.automotivelinux.org/gerrit/p/staging/HomeScreen.git;protocol=http"


do_compile_prepend(){
    PATH=$PATH:${STAGING_BINDIR_NATIVE}/qt5
    export PATH
}

do_install() {
    install -d ${D}/usr/AGL/${PN}
    install -m 0755 ${B}/HomeScreen/HomeScreen ${D}/usr/AGL/${PN}/
    install -d ${D}/usr/AGL/${PN}/colorschemes
    cp -r ${B}/HomeScreen/colorschemes/* ${D}/usr/AGL/${PN}/colorschemes/
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

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/HomeScreen/conf/HomeScreen.service ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/HomeScreenAppFrameworkBinderAGL/conf/HomeScreenAppFrameworkBinderAGL.service ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/InputEventManager/conf/InputEventManager.service ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/WindowManager/conf/WindowManager.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "/usr/AGL/${PN}/ /usr/AGL/${PN}/colorschemes ${libdir} ${systemd_unitdir}/system"
FILES_${PN}-dbg += "/usr/AGL/${PN}/.debug"

#SYSTEMD_PACKAGES - no separate packages
SYSTEMD_SERVICE += "WindowManager.service"
SYSTEMD_SERVICE += "HomeScreen.service"
SYSTEMD_SERVICE += "InputEventManager.service"
SYSTEMD_SERVICE += "HomeScreenAppFrameworkBinderAGL.service"


#############################################
# this has to be set up later...
#############################################
#PACKAGES =+ "libhomescreen libhomescreen-dev libhomescreen-dbg"
#
#FILES_libhomescreen = "\
#	${libdir}/libhomescreen.so.* \
#"
#FILES_libhomescreen-dev = "\
#	${includedir}/libhomescreen.hpp \
#	${libdir}/libhomescreen.so \
#	${libdir}/pkgconfig/libhomescreen.pc \
#"
#FILES_libhomescreen-dbg = "\
#	${libdir}/.debug/libhomescreen.so.* \
#"
#RDEPENDS_libhomescreen-dbg += "${PN}-dbg libhomescreen-dev"
#
#RDEPENDS_${PN}-dev += "libhomescreen-dev"

