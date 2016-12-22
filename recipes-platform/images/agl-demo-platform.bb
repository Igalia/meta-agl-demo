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

# fonts
IMAGE_TTF_FONTS = " \
    ttf-bitstream-vera \
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
    ttf-dejavu-serif \
    "

# add packages for CES2017 demo
IMAGE_INSTALL_append = " \
    ces2017-demo \
    linux-firmware-ath9k \
    linux-firmware-ralink \
    can-utils \
    iproute2 \
    python-curses \
    dhcp-client \
    climatecontrolplugin \
    navigation \
    poiapp \
    ${IMAGE_TTF_FONTS} \
    "
