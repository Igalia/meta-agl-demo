SUMMARY = "The software for application framework of AGL IVI profile"
DESCRIPTION = "A set of packages belong to AGL application framework which required by \
Connectivity Subsystem"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-appfw-connectivity \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    "

AGL_APPS = " \
    "
# restricted due to dependency on Kernel >= 4.8 which is not available in all repositories
AGL_APPS_append_m3ulcb = " agl-service-can-low-level "
AGL_APPS_append_intel-corei7-64 = " agl-service-can-low-level "
AGL_APPS_append_qemux86-64 = " agl-service-can-low-level "

RDEPENDS_${PN}_append = " \
    ${AGL_APPS} \
    "

