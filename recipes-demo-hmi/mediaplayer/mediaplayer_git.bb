SUMMARY     = "Media Player for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating Media Player on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/mediaplayer"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/mediaplayer;protocol=http"
SRCREV  = "${AUTOREV}"

# Pinned branch/SRCREV for Charming Chinook
SRC_URI_chinook = "git://gerrit.automotivelinux.org/gerrit/apps/mediaplayer;protocol=http;branch=chinook"
SRCREV_chinook = "5e9cfc533b6ea757877cedabcba9fdc44ed96897"


PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit qmake5 aglwgt

# build-time dependencies
DEPENDS += "qtquickcontrols2 qtmultimedia"
