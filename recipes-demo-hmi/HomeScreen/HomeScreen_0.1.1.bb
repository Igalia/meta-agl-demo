SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application + Home Screen Simulator + SampleAppTimeDate"
HOMEPAGE    = "https://wiki.automotivelinux.org/homescreen"
LICENSE     = "Apache-2.0"
SECTION     = "apps"
PV          = "0.1.1+gitr${SRCPV}"
PR          = "r1"
S           = "${WORKDIR}/git/"

inherit qmake5
DEPENDS = "qtbase"

LIC_FILES_CHKSUM = "file://HomeScreen/LICENSE;md5=ae6497158920d9524cf208c09cc4c984"
SRCREV  = "3478f65b39560b333ba189e7d86e2c2cebfc9c7a"
SRC_URI = "git://gerrit.automotivelinux.org/gerrit/p/staging/HomeScreen.git;protocol=http"


do_compile_prepend(){
    PATH=$PATH:${STAGING_BINDIR_NATIVE}/qt5
    export PATH
}

do_install() {
    install -d ${D}/opt/AGL/${PN}
    install -m 0755 ${B}/HomeScreen/HomeScreen ${D}/opt/AGL/${PN}/
    install -m 0755 ${B}/SampleAppTimeDate/SampleAppTimeDate ${D}/opt/AGL/${PN}/

    install -d ${D}/opt/AGL/${PN}/colorschemes
    cp ${B}/HomeScreen/colorschemes/* ${D}/opt/AGL/${PN}/colorschemes/
}

FILES_${PN} += "/opt/AGL/${PN}/"
FILES_${PN}-dbg += "/opt/AGL/${PN}/.debug"

