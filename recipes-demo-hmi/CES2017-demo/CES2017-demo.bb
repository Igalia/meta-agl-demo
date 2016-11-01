SUMMARY     = "Homescreen for the AGL Demonstrator @ CES2017"
DESCRIPTION = "Homescreen apps in QML format for the AGL Demonstrator @ CESS2017"
HOMEPAGE    = "https://git.automotivelinux.org/gerrit/#/admin/projects/AGL/DemoApps/CES2017"

SECTION     = "apps"

PN = "ces2017-demo"

LICENSE     = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI     = "git://gerrit.automotivelinux.org/gerrit/AGL/DemoApps/CES2017;protocol=http"
SRCREV      = "${AUTOREV}"

PV = "1.0+git"

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
    mkdir -p ${D}/usr/AGL/CES2017/
    cp -rf ./* ${D}/usr/AGL/CES2017/
}

FILES_${PN} = "/usr/AGL/"
