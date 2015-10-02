SUMMARY = "The software for application framework of AGL IVI profile"
DESCRIPTION = "A set of packages belong to AGL application framework"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-appfw \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    packagegroup-agl-appfw-automotive \
    packagegroup-agl-appfw-connectivity \
    packagegroup-agl-appfw-graphics \
    packagegroup-agl-appfw-multimedia \
    packagegroup-agl-appfw-navi-lbs \
    packagegroup-agl-appfw-agl \
    packagegroup-agl-appfw-native \
    packagegroup-agl-appfw-web \
    packagegroup-agl-appfw-security \
    packagegroup-agl-appfw-speech-services \
    "
