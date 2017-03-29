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
# Only for porter as these kernel module sources
# are for the 3.10.x kernel only
MOST_DRIVERS = " "
MOST_DRIVERS_append = " \
    aim-cdev \
    aim-network \
    aim-sound \
    aim-v4l2 \
    hdm-dim2 \
    hdm-i2c \
    hdm-usb \
    mocca-usb \
    mostcore \
    "

# HVAC dependencies depend on drivers above
IMAGE_MOST_HVAC = " "
IMAGE_MOST_HVAC_append = " \
    ${MOST_DRIVERS} \
    unicens \
    vod-server \
    "

# can-lin is a binary and only for porter :(
IMAGE_MOST_HVAC_append_porter = " \
    can-lin \
    "

# mapviewer and mapviewer-demo requires AGL CES2017 demo mock-up
IMAGE_MAPVIEWER = " "
IMAGE_MAPVIEWER_append_porter = " \
    mapviewer \
    mapviewer-demo \
    "

IMAGE_AGL_APPS = " \
    controls \
    dashboard \
    hvac \
    mediaplayer \
    mixer \
    navigation \
    poiapp \
    phone \
    radio \
    settings \
    "

IMAGE_INSTALL_append = " \
    linux-firmware-ath9k \
    linux-firmware-ralink \
    can-utils \
    iproute2 \
    python-curses \
    dhcp-client \
    qtquickcontrols2-agl \
    qtquickcontrols2-agl-style \
    ${IMAGE_MAPVIEWER} \
    ${IMAGE_MOST_HVAC} \
    ${IMAGE_AGL_APPS} \
    ${IMAGE_TTF_FONTS} \
    "
