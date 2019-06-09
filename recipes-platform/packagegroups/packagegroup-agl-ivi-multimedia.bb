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

# for now: enable here for the AGL IVI demo (image-ivi and demo-platform)
# tbd: change based on usage in profiles
PIPEWIRE = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'pipewire', 'packagegroup-pipewire', '', d)}\
    "

RDEPENDS_${PN} += "\
    ${PIPEWIRE} \
    "
