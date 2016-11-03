SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application + SampleAppTimeDate + HomeScreenAppFrameworkBinderTizen + WindowManager + InputEventManager"
HOMEPAGE    = "https://wiki.automotivelinux.org/homescreen"
LICENSE     = "Apache-2.0"
SECTION     = "apps"
PV          = "0.4.0+gitr${SRCPV}"
PR          = "r1"
S           = "${WORKDIR}/git/"

inherit qmake5
DEPENDS = " qtbase "

# for HomeScreenAppFrameworkBinderTizen:
DEPENDS += " pkgmgr-info aul "
# for WindowManager:
DEPENDS += " wayland-ivi-extension "
# for libhomescreen
DEPENDS += " glib-2.0 "

LIC_FILES_CHKSUM = "file://HomeScreen/LICENSE;md5=ae6497158920d9524cf208c09cc4c984"
SRCREV  = "ec688535558c31989e7da221b858328b2e0766c8"
SRC_URI = "git://gerrit.automotivelinux.org/gerrit/p/staging/HomeScreen.git;protocol=http"


do_compile_prepend(){
    PATH=$PATH:${STAGING_BINDIR_NATIVE}/qt5
    export PATH
}

do_install() {
    install -d ${D}/opt/AGL/${PN}
    install -m 0755 ${B}/HomeScreen/HomeScreen ${D}/opt/AGL/${PN}/
    install -d ${D}/opt/AGL/${PN}/colorschemes
    cp -r ${B}/HomeScreen/colorschemes/* ${D}/opt/AGL/${PN}/colorschemes/
    install -m 0755 ${B}/SampleAppTimeDate/SampleAppTimeDate ${D}/opt/AGL/${PN}/
    install -m 0755 ${B}/SampleHomeScreenInterfaceApp/SampleHomeScreenInterfaceApp ${D}/opt/AGL/${PN}/
    install -m 0755 ${B}/HomeScreenAppFrameworkBinderTizen/HomeScreenAppFrameworkBinderTizen ${D}/opt/AGL/${PN}/
    install -m 0755 ${B}/WindowManager/WindowManager ${D}/opt/AGL/${PN}/
    install -m 0755 ${B}/InputEventManager/InputEventManager ${D}/opt/AGL/${PN}/
    
    install -d ${D}/usr/lib
    install -m 0644 ${B}/libhomescreen/libhomescreen.so.1.0.0 ${D}/usr/lib/
    ln -sf /usr/lib/libhomescreen.so.1.0.0 ${D}/usr/lib/libhomescreen.so
    ln -sf /usr/lib/libhomescreen.so.1.0.0 ${D}/usr/lib/libhomescreen.so.1
    ln -sf /usr/lib/libhomescreen.so.1.0.0 ${D}/usr/lib/libhomescreen.so.1.0
}


FILES_${PN} += "/opt/AGL/${PN}/ /opt/AGL/${PN}/colorschemes /usr/lib/"
FILES_${PN}-dbg += "/opt/AGL/${PN}/.debug"

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

