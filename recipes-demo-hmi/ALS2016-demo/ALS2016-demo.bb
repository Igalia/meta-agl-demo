SUMMARY     = "Homescreen for the AGL Demonstrator @ ALS2016"
DESCRIPTION = "Homescreen apps in QML format for the AGL Demonstrator @ ALS2016"
HOMEPAGE    = "https://git.automotivelinux.org/gerrit/#/admin/projects/AGL/DemoApps/ALS2016"

SECTION     = "apps"

LICENSE     = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI     = "git://gerrit.automotivelinux.org/gerrit/AGL/DemoApps/ALS2016;protocol=http"
SRCREV      = "02c98d910f092067655ea7599b3595020ed335e5"
# for development use:
#SRCREV      = "${AUTOREV}"

PV = "2.0.1"

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
    mkdir -p ${D}/opt/AGL/ALS2016/
    cp -ar ./* ${D}/opt/AGL/ALS2016/
}

FILES_${PN} = "/opt/AGL/"
