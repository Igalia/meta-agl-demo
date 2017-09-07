SUMMARY     = "Mixer for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI application for control of PulseAudio mixer elements"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/mixer"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/mixer;protocol=https;branch=dab"

SRCREV = "b6ea8b3afbd244dd7f56eeb9886fb108b58e3765"
SRCREV_dab = "refs/tags/${DISTRO_BRANCH_VERSION_TAG}"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit qmake5 aglwgt

# build-time dependencies
DEPENDS += "qtquickcontrols2 pulseaudio"
