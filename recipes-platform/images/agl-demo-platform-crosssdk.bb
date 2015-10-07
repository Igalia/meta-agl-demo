SUMMARY = "Cross SDK of Full AGL Distribution for IVI profile"

DESCRIPTION = "SDK image for full AGL Distribution for IVI profile. \
It includes the full meta-toolchain, plus developement headers and libraries \
to form a standalone cross SDK."

require agl-demo-platform.bb

IMAGE_FEATURES_append = "dev-pkgs"
IMAGE_INSTALL_append = "kernel-dev"

inherit populate_sdk populate_sdk_qt5
