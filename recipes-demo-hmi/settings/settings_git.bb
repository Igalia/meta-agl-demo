SUMMARY     = "Settings for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating Settings on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/settings"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/settings;protocol=https;branch=dab"
SRCREV  = "32be14bbc8f05abaf7ef5cb3e96469afc193d1f0"
SRCREV_dab = "refs/tags/${DISTRO_BRANCH_VERSION_TAG}"


PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2"

# runtime dependencies
RDEPENDS_${PN} += " \
            agl-service-bluetooth \
            agl-service-wifi"

inherit qmake5 aglwgt
