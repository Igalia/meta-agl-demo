SUMMARY     = "Dashboard for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating dashboard on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/dashboard"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

#SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/dashboard;protocol=https;branch=${AGL_BRANCH}"
#SRCREV  = "${AGL_APP_REVISION}"
SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/dashboard;protocol=https;branch=sandbox/zheng_wenlong/qtaglextras"
SRCREV  = "9c6150f2108554210fb1ecf77175b30db8b37026"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2 qttools-native qtaglextras"

inherit qmake5 aglwgt
