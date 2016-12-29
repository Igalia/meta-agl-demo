SUMMARY     = "Mixer for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI application for control of PulseAudio mixer elements"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/mixer"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/mixer;protocol=http"
SRCREV  = "${AUTOREV}"

# Pinned branch/SRCREV for Charming Chinook
SRC_URI_chinook = "git://gerrit.automotivelinux.org/gerrit/apps/mixer;protocol=http;branch=chinook"
SRCREV_chinook = "1c1d5ee5902ebc013fa180ec7d1233147aa29c65"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit qmake5 aglwgt

# build-time dependencies
DEPENDS += "qtquickcontrols2 pulseaudio"
