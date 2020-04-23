DESCRIPTION = "The minimal set of packages for AGL IVI Connectivity Subsystem"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-ivi-services \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
agl-service-bluetooth \
agl-service-bluetooth-map \
agl-service-bluetooth-pbap \
agl-service-can-low-level \
agl-service-geoclue \
agl-service-geofence \
agl-service-gps \
agl-service-identity-agent \
agl-service-iiodevices \
agl-service-mediascanner \
agl-service-navigation \
agl-service-nfc \
agl-service-signal-composer \
agl-service-steering-wheel \
agl-service-unicens \
agl-service-weather \
    "
