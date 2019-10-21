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

# MOST out-of-tree kernel drivers
#################################
MOST_DRIVERS ??= " \
    most \
    "
# These boards use different kernels - needs to be checked
MOST_DRIVERS_dra7xx-evm ?= ""
MOST_DRIVERS_dragonboard-410c ?= ""


# HVAC dependencies
###################
LIN_DRIVERS ??= " sllin"
# These boards use different kernels - needs to be checked
LIN_DRIVERS_dra7xx-evm ?= ""
LIN_DRIVERS_dragonboard-410c ?= ""

# UNICENS service
UNICENS ?= " \
    unicens-config \
    agl-service-unicens \
    agl-service-unicens-controller \
    "


AGL_APPS = " \
    dashboard \
    hvac \
    mediaplayer \
    virtual/navigation \
    phone \
    poiapp \
    radio \
    settings \
    messaging \
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'taskmanager' , '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'unzip' , '', d)} \
    high-level-viwi-service \
    agl-service-signal-composer \
    low-can-demo \
    virtual/mixer \
    "

AGL_APIS = " \
    libnaviapi-agl \
    "

QTAGLEXTRAS = "${@bb.utils.contains("DISTRO_FEATURES", "agl-hmi-framework", " qtaglextras", "",d)}"

# Cluster demo support.
# ATM no cluster map viewer is supported with the older navigation application.
MAPVIEWER = "${@bb.utils.contains("PREFERRED_RPROVIDER_virtual/navigation", "ondemandnavi", "tbtnavi", "",d)}"
CLUSTER_SUPPORT = "${@bb.utils.contains("DISTRO_FEATURES", "agl-cluster-demo-support", "${MAPVIEWER} cluster-demo-network-config", "",d)}"

# Preload poi API key for demo if requested, and potentially maps for older
# navigation application if it is configured.
DEMO_MAPS_LOCALE ?= "uk"
DEMO_PRELOAD_MAPS = "${@bb.utils.contains("PREFERRED_RPROVIDER_virtual/navigation", "navigation", " navigation-maps-${DEMO_MAPS_LOCALE}", "",d)}"
DEMO_PRELOAD = "${@bb.utils.contains("DISTRO_FEATURES", "agl-demo-preload", " ${DEMO_PRELOAD_MAPS} poiapp-api-key", "",d)}"

# Hook for demo platform configuration
# ATM, only used to disable btwilink module on M3ULCB + Kingfisher by default,
# setting DEMO_ENABLE_BTWILINK to "true" in local.conf / site.conf re-enables.
DEMO_ENABLE_BTWILINK ?= ""
DEMO_PLATFORM_CONF = ""
DEMO_PLATFORM_CONF_append_m3ulcb = "${@bb.utils.contains("DEMO_ENABLE_BTWILINK", "true", "", " btwilink-disable-conf", d)}"

RDEPENDS_${PN}_append = " \
    qtquickcontrols2-agl \
    qtquickcontrols2-agl-style \
    linux-firmware-ralink \
    ${UNICENS} \
    ${MOST_DRIVERS} \
    ${LIN_DRIVERS} \
    ${AGL_APPS} \
    ${AGL_APIS} \
    ${QTAGLEXTRAS} \
    ${CLUSTER_SUPPORT} \
    ${DEMO_PRELOAD} \
    ${DEMO_PLATFORM_CONF} \
    "
