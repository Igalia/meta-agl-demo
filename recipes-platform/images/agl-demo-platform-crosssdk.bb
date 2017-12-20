SUMMARY = "Cross SDK of Full AGL Distribution for IVI profile"

DESCRIPTION = "SDK image for full AGL Distribution for IVI profile. \
It includes the full meta-toolchain, plus developement headers and libraries \
to form a standalone cross SDK."

require agl-demo-platform.bb

LICENSE = "MIT"

IMAGE_FEATURES_append = " dev-pkgs"
IMAGE_INSTALL_append = " kernel-dev kernel-devsrc"

inherit populate_sdk populate_sdk_qt5

# Task do_populate_sdk and do_rootfs can't be exec simultaneously.
# Both exec "createrepo" on the same directory, and so one of them
# can failed (randomly).
addtask do_populate_sdk after do_rootfs

# native tools to support Chromium build inside SDK (SPEC-942)
TOOLCHAIN_HOST_TASK += " \
    nativesdk-gn \
    nativesdk-ninja \
    nativesdk-gperf \
    nativesdk-zlib \
    nativesdk-xz \
    nativesdk-nspr-dev \
    nativesdk-nss-dev \
    nativesdk-lua \
    "

# required dependencies for Chromium build inside SDK (SPEC-942)
TOOLCHAIN_TARGET_TASK += " \
    pciutils-dev \
    pulseaudio-dev \
    cairo-dev \
    nss-dev \
    cups-dev \
    gconf-dev \
    libexif-dev \
    pango-dev \
    libdrm-dev  \
    libssp-dev \
    ${@bb.utils.contains('LICENSE_FLAGS_WHITELIST', 'commercial', 'ffmpeg-dev', '', d)} \
    "

# Add wayland-scanner to SDK (SPEC-945)
# Use TOOLCHAIN_HOST_TASK instead of adding to the packagegroup
# wayland-scanner is in nativesdk-wayland-dev !
# option: add also nativesdk-qtwayland-tools
TOOLCHAIN_HOST_TASK_append = " nativesdk-wayland nativesdk-wayland-dev"

TOOLCHAIN_HOST_TASK_append = " nativesdk-perl-modules "
