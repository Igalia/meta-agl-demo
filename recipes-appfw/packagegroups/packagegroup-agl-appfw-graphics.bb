SUMMARY = "The software for application framework of AGL IVI profile"
DESCRIPTION = "A set of packages belong to AGL application framework which required by \
Graphics Subsystem"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-appfw-graphics \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    opencv \
    "
