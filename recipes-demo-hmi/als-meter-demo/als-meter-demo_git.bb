SUMMARY     = "Demo app for streeting-wheel"
DESCRIPTION = "AGL demo app for streeting-wheel"
HOMEPAGE    = "http://192.168.160.148/shenhui/als-meter-demo-release"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/als-meter-demo;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AGL_APP_REVISION}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2 opencv"

# runtime dependencies
RDEPENDS_${PN} += "agl-service-steering-wheel"

inherit qmake5 aglwgt
