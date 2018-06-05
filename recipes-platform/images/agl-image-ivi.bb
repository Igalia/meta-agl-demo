SUMMARY = "A basic system of AGL distribution of IVI profile"

DESCRIPTION = "Basic image for baseline of AGL Distribution for IVI profile."

require agl-image-ivi.inc

LICENSE = "MIT"

IMAGE_INSTALL_append = "\
    packagegroup-agl-image-ivi \
    "

DISTRO_FEATURES_append = " agl-core-image-profile"

IMAGE_INSTALL += "\
    agl-desktop-config \
    "
