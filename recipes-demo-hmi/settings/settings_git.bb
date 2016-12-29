SUMMARY     = "Settings for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating Settings on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/settings"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/settings;protocol=http"
SRCREV  = "${AUTOREV}"

# Pinned branch/SRCREV for Charming Chinook
SRC_URI_chinook = "git://gerrit.automotivelinux.org/gerrit/apps/settings;protocol=http;branch=chinook"
SRCREV_chinook = "28b20998d20f69b65adcc174f9073a93f53af79e"


PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2"

inherit qmake5 aglwgt
