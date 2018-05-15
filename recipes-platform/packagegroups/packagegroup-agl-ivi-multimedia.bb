SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "The set of packages required by Multimedia Subsystem"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-ivi-multimedia \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    lightmediascanner-meta \
    "

RDEPENDS_${PN} += "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-audio-4a-framework', '' , 'virtual/pulseaudio-config', d)} \
    "
