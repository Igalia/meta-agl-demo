SUMMARY = "The software for DEMO platform of AGL IVI profile"
DESCRIPTION = "A set of packages belong to AGL Demo Platform"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-demo-platform \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    packagegroup-agl-image-ivi \
    "

RDEPENDS_${PN} += "\
    packagegroup-agl-demo \
    "

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
MOST_HVAC = " "
MOST_HVAC_append = " \
    ${MOST_DRIVERS} \
    unicens \
    vod-server \
    "

# can-lin is a binary and only for porter :(
MOST_HVAC_append_porter = " \
    can-lin \
    "

# mapviewer and mapviewer-demo requires AGL CES2017 demo mock-up
MAPVIEWER = " "
MAPVIEWER_append_porter = " \
    mapviewer \
    mapviewer-demo \
    "

AGL_APPS = " \
    controls \
    dashboard \
    hvac \
    mediaplayer \
    mixer \
    navigation \
    phone \
    poiapp \
    radio \
    settings \
    "

RDEPENDS_${PN}_append = " \
    ces2017-demo \
    linux-firmware-ralink \
    ${MAPVIEWER} \
    ${MOST_HVAC} \
    ${AGL_APPS} \
    "


