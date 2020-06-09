DESCRIPTION = "The minimal set of services to support AGL IVI demo"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-ivi-services \
    "

RDEPENDS_${PN} += "\
    agl-service-bluetooth \
    agl-service-bluetooth-map \
    agl-service-bluetooth-pbap \
    agl-service-can-low-level \
    agl-service-geoclue \
    agl-service-geofence \
    agl-service-gps \
    agl-service-hvac \
    agl-service-identity-agent \
    agl-service-iiodevices \
    agl-service-mediascanner \
    agl-service-navigation \
    agl-service-nfc \
    agl-service-signal-composer \
    agl-service-telephony \
    agl-service-unicens \
    agl-service-unicens-controller \
    agl-service-weather \
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'agl-service-taskmanager', '', d)} \
    "
