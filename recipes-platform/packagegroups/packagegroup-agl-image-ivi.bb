SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "The set of packages required for AGL Distribution"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-image-ivi \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    packagegroup-agl-image-minimal \
"

RDEPENDS_${PN} += "\
    packagegroup-agl-ivi-connectivity \
    packagegroup-agl-ivi-graphics \
    packagegroup-agl-ivi-multimedia \
    "
#    packagegroup-agl-ivi-automotive \
#    packagegroup-agl-ivi-speech-services \
#    packagegroup-agl-ivi-navi-lbs \
#    packagegroup-agl-ivi-security \
#    packagegroup-agl-ivi-os-commonlibs \
#    packagegroup-agl-ivi-kernel \
#

RDEPENDS_${PN} += "\
    agl-login-manager \
    "
