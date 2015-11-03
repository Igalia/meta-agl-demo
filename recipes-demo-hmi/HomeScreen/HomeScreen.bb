SUMMARY     = "${PN} for the AGL Demo Platform"
DESCRIPTION = "${PN} app"
HOMEPAGE    = "https://git.automotivelinux.org/gerrit/#/admin/projects/AGL/DemoApps/${PN}"

SECTION     = "apps"

# To be revised once sourcecode lands
# LICENSE     = "xyz"
# Dummy MPL-2.0
LICENSE     = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"


SRC_URI     = "git://git.automotivelinux.org/gerrit/AGL/DemoApps/${PN};protocol=http"
SRCREV      = "AUTOINC"

# custom configure and install as these are just qml files

# we work in the git checkout ...
S = "${WORKDIR}/git"

do_configure() {
    true
}

# empty folder for now
do_install() {
    mkdir -p ${D}/opt/AGL/${PN}/
}

FILES_${PN} = "/opt/AGL/${PN}/"
