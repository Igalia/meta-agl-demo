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
    packagegroup-agl-profile-graphical-qt5 \
    packagegroup-agl-demo \
    "

MOST_DRIVERS = " \
    most \
    "
MOST_DRIVERS_append_m3ulcb = " sllin"

# HVAC dependencies depend on drivers above
MOST_HVAC = " "
MOST_HVAC_append = " \
    ${MOST_DRIVERS} \
    unicens-config \
    agl-service-unicens \
    "

# @Chris: This needs to be checked!
MOST_HVAC_dra7xx-evm = ""
MOST_HVAC_dragonboard-410c = ""

AGL_APPS = " \
    dashboard \
    hvac \
    mediaplayer \
    virtual/mixer  \
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

# mapviewer and mapviewer-demo are required for AGL cluster demo
MAPVIEWER = "${@bb.utils.contains("DISTRO_FEATURES", "agl-cluster-demo-support", " mapviewer mapviewer-demo", "",d)}"

RDEPENDS_${PN}_append = " \
    qtquickcontrols2-agl \
    qtquickcontrols2-agl-style \
    linux-firmware-ralink \
    ${MOST_HVAC} \
    ${AGL_APPS} \
    ${AGL_APIS} \
    ${QTAGLEXTRAS} \
    ${MAPVIEWER} \
    "


