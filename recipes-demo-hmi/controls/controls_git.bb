SUMMARY     = "Controls for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating UI components on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/controls"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/controls;protocol=https;branch=dab"
SRCREV  = "b8d54a3e02cddf3bdd006a9285a4c899a08175d9"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2"

inherit qmake5 aglwgt
