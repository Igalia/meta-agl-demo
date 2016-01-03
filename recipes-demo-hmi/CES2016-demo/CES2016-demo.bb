SUMMARY     = "Homescreen for the AGL Demonstrator @ CES2016"
DESCRIPTION = "Homescreen apps in QML format for the AGL Demonstrator @ CES2016"
HOMEPAGE    = "https://git.automotivelinux.org/gerrit/#/admin/projects/AGL/DemoApps/CES2016"

SECTION     = "apps"

LICENSE     = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI     = "git://git.automotivelinux.org/gerrit/AGL/DemoApps/CES2016;protocol=http;branch=albacore"
SRCREV      = "0ca1123264b79512f649952e23f71e665266fe72"

PV = "1.0+albacore_1.0"

RDEPENDS_${PN} += " \
    qtmultimedia-qmlplugins \
    qtquickcontrols-qmlplugins \
    qtsvg-plugins \
    "

# custom configure and install as these are just qml files

# we work in the git checkout ...
S = "${WORKDIR}/git"

do_configure() {
    ls
}

# plain copy in own folder for now
do_install() {
    mkdir -p ${D}/opt/AGL/CES2016/
    cp -ar ./* ${D}/opt/AGL/CES2016/
}

FILES_${PN} = "/opt/AGL/"
