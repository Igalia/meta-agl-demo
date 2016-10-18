SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application + SampleAppTimeDate + HomeScreenAppFrameworkBinderTizen + WindowManager + InputEventManager"
HOMEPAGE    = "https://wiki.automotivelinux.org/homescreen"
LICENSE     = "Apache-2.0"
SECTION     = "apps"
PV          = "0.3.3+gitr${SRCPV}"
PR          = "r1"
S           = "${WORKDIR}/git/"

inherit qmake5
DEPENDS = " qtbase "

# for HomeScreenAppFrameworkBinderTizen:
DEPENDS += " pkgmgr-info aul "
# for WindowManager:
DEPENDS += " wayland-ivi-extension "

LIC_FILES_CHKSUM = "file://HomeScreen/LICENSE;md5=ae6497158920d9524cf208c09cc4c984"
SRCREV  = "824bfd42b0105b0aeffbc33a87285578abda2f3b"
SRC_URI = "git://gerrit.automotivelinux.org/gerrit/p/staging/HomeScreen.git;protocol=http"


do_compile_prepend(){
    PATH=$PATH:${STAGING_BINDIR_NATIVE}/qt5
    export PATH
}

do_install() {
    install -d ${D}/opt/AGL/${PN}
    install -m 0755 ${B}/HomeScreen/HomeScreen ${D}/opt/AGL/${PN}/
    install -m 0755 ${B}/SampleAppTimeDate/SampleAppTimeDate ${D}/opt/AGL/${PN}/
    install -m 0755 ${B}/HomeScreenAppFrameworkBinderTizen/HomeScreenAppFrameworkBinderTizen ${D}/opt/AGL/${PN}/
    install -m 0755 ${B}/WindowManager/WindowManager ${D}/opt/AGL/${PN}/
    install -m 0755 ${B}/InputEventManager/InputEventManager ${D}/opt/AGL/${PN}/


    install -d ${D}/opt/AGL/${PN}/colorschemes
    cp -r ${B}/HomeScreen/colorschemes/* ${D}/opt/AGL/${PN}/colorschemes/
}

FILES_${PN} += "/opt/AGL/${PN}/"
FILES_${PN}-dbg += "/opt/AGL/${PN}/.debug"

