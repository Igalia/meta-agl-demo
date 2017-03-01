SUMMARY     = "Controls for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating UI components on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/controls"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/controls;protocol=http"
SRCREV  = "${AUTOREV}"

# Pinned branch/SRCREV for Charming Chinook
SRC_URI_chinook = "git://gerrit.automotivelinux.org/gerrit/apps/controls;protocol=http;branch=chinook"
SRCREV_chinook = "f6e78f4b47f2bdc3e618c30deb4e4d11f71d30a0"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2"

inherit qmake5 aglwgt
