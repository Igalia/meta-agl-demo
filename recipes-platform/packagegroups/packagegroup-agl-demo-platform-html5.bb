SUMMARY = "The software for DEMO platform of AGL HTML5 profile"
DESCRIPTION = "Packages required to demo the HTML5 profile and sample web apps"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-demo-platform-html5 \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    packagegroup-agl-image-ivi \
    packagegroup-agl-profile-graphical-html5 \
    packagegroup-agl-demo \
    "

AGL_APPS = " \
    html5-launcher \
    html5-hvac \
    html5-settings \
    "

RDEPENDS_${PN}_append = " \
    ${AGL_APPS} \
    "
