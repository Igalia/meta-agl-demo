SUMMARY = "The software for DEMO platform of AGL IVI profile"
DESCRIPTION = "A set of packages belong to AGL Demo Platform"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-demo-platform-html5 \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    packagegroup-agl-image-ivi \
    "

# add packages for demo platform (include demo apps) here
RDEPENDS_${PN} += " \
    packagegroup-agl-demo \
    "

# add packages for OpenIVI-HTML5 demo
RDEPENDS_${PN} += " \
    openivi-html5 \
    "
