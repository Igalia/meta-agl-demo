SUMMARY     = "AGL Home Screen Library"
DESCRIPTION = "libhomescreen"
HOMEPAGE    = "http://docs.automotivelinux.org"
LICENSE     = "Apache-2.0"
SECTION     = "libs"

BBCLASSEXTEND = " nativesdk"

LIC_FILES_CHKSUM = "file://libhomescreen/LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

DEPENDS += " glib-2.0 "

inherit qmake5 pkgconfig

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/p/src/libhomescreen.git;protocol=https;branch=${AGL_BRANCH} \
           file://homescreen.pc.in"
SRCREV  = "f5c14333d2170ae9babed53ea530828569a830ff"
S       = "${WORKDIR}/git/"

# PV needs to be modified with SRCPV to work AUTOREV correctly
PV = "0.0+git${SRCPV}"

do_install() {
    install -d ${D}/usr/AGL/${PN}
    install -m 0755 ${B}/samplehomescreeninterfaceapp/SampleHomeScreenInterfaceApp ${D}/usr/AGL/${PN}/

    install -d ${D}${libdir}
    install -m 0644 ${B}/libhomescreen/libhomescreen.so.1.0.0 ${D}${libdir}/
    ln -sf libhomescreen.so.1.0.0 ${D}${libdir}/libhomescreen.so
    ln -sf libhomescreen.so.1.0.0 ${D}${libdir}/libhomescreen.so.1
    ln -sf libhomescreen.so.1.0.0 ${D}${libdir}/libhomescreen.so.1.0

# kooltux: still some problem with paths inside .pc file
# error at build time:
# ERROR: homescreen-git-r0 do_populate_sysroot: QA Issue: homescreen.pc failed sanity test (tmpdir) in path /xdt/build/tmp/work/cortexa15hf-neon-agl-linux-gnueabi/libhomescreen/git-r0/sysroot-destdir/usr/lib/pkgconfig [pkgconfig]
#
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/homescreen.pc.in ${D}${libdir}/pkgconfig/homescreen.pc
    sed -i s:OEPREFIX:${prefix}:g ${D}${libdir}/pkgconfig/homescreen.pc
    sed -i s:OELIBDIR:${libdir}:g ${D}${libdir}/pkgconfig/homescreen.pc
    sed -i s:OEINCDIR:${includedir}:g ${D}${libdir}/pkgconfig/homescreen.pc

    install -d ${D}${includedir}
    install -m 0644 ${S}/libhomescreen/include/libhomescreen.hpp ${D}${includedir}/
}

PACKAGES =+ "libhomescreensampleapp libhomescreensampleapp-dbg"

FILES_${PN} = "\
	${libdir}/libhomescreen.so.* \
"
FILES_${PN}-dev = "\
	${includedir}/libhomescreen.hpp \
	${libdir}/libhomescreen.so \
	${libdir}/pkgconfig/homescreen.pc \
"
FILES_${PN}-dbg += "\
	${libdir}/.debug/libhomescreen.so.* \
"

FILES_libhomescreensampleapp += "/usr/AGL/${PN}/SampleHomeScreenInterfaceApp"
FILES_libhomescreensampleapp-dbg += "/usr/AGL/${PN}/.debug"


RDEPENDS_libhomescreensampleapp-dbg += "${PN}-dbg ${PN}-dev"
PROVIDES += "virtual/libhomescreen"
