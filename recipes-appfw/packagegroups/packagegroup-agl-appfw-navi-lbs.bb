SUMMARY = "The software for application framework of AGL IVI profile"
DESCRIPTION = "A set of packages belong to AGL application framework which required by \
Navigation and Location-Based Services Subsystem"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-appfw-navi-lbs \
    "

RDEPENDS_${PN} += "\
    agl-service-gps \
    agl-service-geoclue \
    agl-service-geofence \
    "
