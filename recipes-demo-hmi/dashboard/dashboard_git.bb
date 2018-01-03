SUMMARY     = "Dashboard for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating dashboard on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/dashboard"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/dashboard;protocol=https;branch=sandbox/ruke47/ces2018"
SRCREV  = "${AUTOREV}"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2 virtual/libhomescreen qlibwindowmanager qttools-native"

inherit qmake5 aglwgt
