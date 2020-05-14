SUMMARY     = "Settings for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating Settings on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/settings"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/settings;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AGL_APP_REVISION}"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "libqtappfw qtquickcontrols2 qt-qrcode"
DEPENDS += "libhomescreen qtvirtualkeyboard"

# runtime dependencies
RDEPENDS_${PN} += " \
	qt-qrcode \
	agl-service-bluetooth \
	agl-service-network \
	libqtappfw \
"

inherit qmake5 aglwgt
