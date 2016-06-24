# Base image
require recipes-ivi/images/agl-image-ivi.inc

DESCRIPTION = "AGL Demo Platform image currently contains a simple HMI and \
demos."

IMAGE_FEATURES_append = " \
    "

# add packages for demo platform (include demo apps) here
IMAGE_INSTALL_append = " \
    packagegroup-agl-core \
    packagegroup-agl-ivi \
    packagegroup-ivi-common-core \
    packagegroup-agl-demo-platform \
    "

# add packages for CES2016 demo
IMAGE_INSTALL_append = " \
    ALS2016-demo \
    CES2016-demo \
    can-utils iproute2 \
    python-curses \
    dhcp-client \
    climatecontrolplugin \
    Navigation \
    "
