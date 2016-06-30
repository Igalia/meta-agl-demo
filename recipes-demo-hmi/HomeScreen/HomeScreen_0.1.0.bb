SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application + Home Screen Simulator + SampleAppTimeDate"
HOMEPAGE    = "https://wiki.automotivelinux.org/homescreen"
LICENSE     = "Apache-2.0"
SECTION     = "apps"
PV          = "0.1.0+gitr${SRCPV}"
PR          = "r1"
S           = "${WORKDIR}/git/"

inherit qmake5
DEPENDS = "qtbase"

LIC_FILES_CHKSUM = "file://HomeScreen/LICENSE;md5=ae6497158920d9524cf208c09cc4c984"
SRCREV  = "cf47f13164830e5b153db006cc55c3cf126d8d8c"
SRC_URI = "git://gerrit.automotivelinux.org/gerrit/p/staging/HomeScreen.git;protocol=http"


do_compile_prepend(){
    PATH=$PATH:${STAGING_BINDIR_NATIVE}/qt5
    export PATH
}

do_install() {
    install -d ${D}/opt/AGL/${PN}
    install -m 0755 ${B}/HomeScreen/HomeScreen ${D}/opt/AGL/${PN}/
    install -m 0755 ${B}/SampleAppTimeDate/SampleAppTimeDate ${D}/opt/AGL/${PN}/
}

FILES_${PN} += "/opt/AGL/${PN}/"
FILES_${PN}-dbg += "/opt/AGL/${PN}/.debug"

