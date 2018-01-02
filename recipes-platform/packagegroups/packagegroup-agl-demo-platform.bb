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
    mocca-usb \
    most \
    "
# Don't add MOST support when building for Porter
MOST_DRIVERS_remove_porter = "most"

# HVAC dependencies depend on drivers above
MOST_HVAC = " "
MOST_HVAC_append = " \
    ${MOST_DRIVERS} \
    unicens-config \
    agl-service-unicens \
    "

# can-lin is a binary and only for porter :(
MOST_HVAC_append_porter = " \
    can-lin \
    "

# mapviewer and mapviewer-demo requires AGL CES2017 demo mock-up
MAPVIEWER = "${@bb.utils.contains("DISTRO_FEATURES", "agl-mapviewer-demo", " mapviewer mapviewer-demo", "",d)}"

AGL_APPS = " \
    dashboard \
    hvac \
    mediaplayer \
    mixer \
    virtual/navigation \
    phone \
    poiapp \
    radio \
    settings \
    high-level-viwi-service \
    agl-service-signal-composer \
    low-can-demo \
    "

AGL_APIS = " \
    libnaviapi-agl \
    "

QTAGLEXTRAS = "${@bb.utils.contains("DISTRO_FEATURES", "agl-hmi-framework", " qtaglextras", "",d)}"

RDEPENDS_${PN}_append = " \
    qtquickcontrols2-agl \
    qtquickcontrols2-agl-style \
    linux-firmware-ralink \
    ${QTAGLEXTRAS} \
    ${MAPVIEWER} \
    ${MOST_HVAC} \
    ${AGL_APPS} \
    ${AGL_APIS} \
    "


