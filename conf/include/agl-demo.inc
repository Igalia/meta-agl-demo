IMAGE_FEATURES =+ "debug-tweaks"

# disable the webruntime
#DISTRO_FEATURES_append = " webruntime "
DISTRO_FEATURES_append = " automount "

IMAGE_INSTALL_append = " screen mc "
