SUMMARY     = "HVAC for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating HVAC on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/hvac"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/hvac;protocol=http"
SRCREV  = "559d1a3272a6dbf87139cc3a77beaddfc5f66b63"

# Pinned branch/SRCREV for Charming Chinook
SRC_URI_chinook = "git://gerrit.automotivelinux.org/gerrit/apps/hvac;protocol=http;branch=chinook"
SRCREV_chinook = "31e930c7fbd9a32f200c654a383d29f973d94440"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2"

inherit qmake5 aglwgt
