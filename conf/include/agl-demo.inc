IMAGE_FEATURES =+ "debug-tweaks"

# disable the webruntime
#DISTRO_FEATURES_append = " webruntime "
DISTRO_FEATURES_append = " automount "

IMAGE_INSTALL_append = " screen mc "

# Add local packagegroups to agl-test-wgt and agl-devel-wgt image feature
# package lists
FEATURE_PACKAGES_agl-test-wgt_append = " packagegroup-agl-ivi-services-test"
FEATURE_PACKAGES_agl-devel-wgt_append = " \
    packagegroup-agl-ivi-services-devel \
    packagegroup-agl-demo-platform-devel \
    "

# Extend agl-voiceagent-alexa image feature with local alexa-viewer
# package.
FEATURE_PACKAGES_agl-voiceagent-alexa_append = " alexa-viewer"
