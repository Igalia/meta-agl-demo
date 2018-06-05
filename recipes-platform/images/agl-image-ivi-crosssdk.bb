SUMMARY = "Cross SDK of AGL Distribution for IVI profile"

DESCRIPTION = "Basic image for baseline of AGL Distribution for IVI profile. \
It includes the full meta-toolchain, plus developement headers and libraries \
to form a standalone cross SDK."

require agl-image-ivi.bb

LICENSE = "MIT"

IMAGE_FEATURES += "dev-pkgs"
IMAGE_INSTALL += "kernel-dev"

inherit populate_sdk

# Task do_populate_sdk and do_rootfs can't be exec simultaneously.
# Both exec "createrepo" on the same directory, and so one of them
# can failed (randomly).
addtask do_populate_sdk after do_rootfs
