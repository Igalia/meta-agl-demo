IMAGE_FEATURES =+ "debug-tweaks"

# disable the webruntime
#DISTRO_FEATURES_append = " webruntime "
DISTRO_FEATURES_append = " automount "

IMAGE_INSTALL_append = " screen mc "

# Extend agl-voiceagent-alexa image feature with local alexa-viewer
# package.
FEATURE_PACKAGES_agl-voiceagent-alexa_append = " alexa-viewer"
