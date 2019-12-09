SUMMARY = "DEMO platform of AGL HTML5 profile"
DESCRIPTION = "Contains the web runtime and sample web apps"

require agl-demo-platform.inc

LICENSE = "MIT"

IMAGE_FEATURES_append = " \
    "

# add packages for demo platform (include demo apps) here
IMAGE_INSTALL_append = " \
    packagegroup-agl-demo-platform-html5 \
    "
