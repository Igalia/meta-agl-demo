SUMMARY     = "HVAC for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating HVAC on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/hvac"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/hvac;protocol=https;branch=dab"
SRCREV = "0d6f3ccbfdfe7947517879a1b034ba9cad8247f1"
SRCREV_dab = "refs/tags/${DISTRO_BRANCH_VERSION_TAG}"


PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2"

inherit qmake5 aglwgt
