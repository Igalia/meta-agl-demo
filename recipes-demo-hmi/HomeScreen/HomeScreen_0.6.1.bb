SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application + SampleAppTimeDate + HomeScreenAppFrameworkBinderAGL + WindowManager + InputEventManager + two sample apps (QML and Qtwidget)"
HOMEPAGE    = "https://wiki.automotivelinux.org/homescreen"
LICENSE     = "Apache-2.0"
SECTION     = "apps"
PV          = "0.6.1+gitr${SRCPV}"
PR          = "r1"
S           = "${WORKDIR}/git/"

inherit qmake5
DEPENDS = " qtbase "

# for HomeScreenAppFrameworkBinderTizen:
#DEPENDS += " pkgmgr-info aul "
# for WindowManager:
DEPENDS += " wayland-ivi-extension "
# for libhomescreen
DEPENDS += " glib-2.0 "

LIC_FILES_CHKSUM = "file://HomeScreen/LICENSE;md5=ae6497158920d9524cf208c09cc4c984"
SRCREV  = "61d3f0e1e2210d6108953b0433324a3365d9dab6"
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
    
    install -d ${D}/usr/lib
    install -m 0644 ${B}/libhomescreen/libhomescreen.so.1.0.0 ${D}/usr/lib/
    ln -sf /usr/lib/libhomescreen.so.1.0.0 ${D}/usr/lib/libhomescreen.so
    ln -sf /usr/lib/libhomescreen.so.1.0.0 ${D}/usr/lib/libhomescreen.so.1
    ln -sf /usr/lib/libhomescreen.so.1.0.0 ${D}/usr/lib/libhomescreen.so.1.0
}


FILES_${PN} += "/usr/AGL/${PN}/ /usr/AGL/${PN}/colorschemes /usr/lib/"
FILES_${PN}-dbg += "/usr/AGL/${PN}/.debug"

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

