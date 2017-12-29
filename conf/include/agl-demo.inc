IMAGE_FEATURES =+ "debug-tweaks"

DISTRO_FEATURES_append = " webruntime "
DISTRO_FEATURES_append = " automount "

IMAGE_INSTALL_append = " screen mc "

# CES2018 Hack of map viewer for demo of IC combination
DISTRO_FEATURES_append_m3ulcb = " virtual-display gst-record agl-mapviewer-demo"
