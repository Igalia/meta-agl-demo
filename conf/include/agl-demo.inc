IMAGE_FEATURES =+ "debug-tweaks"

DISTRO_FEATURES_append = " webruntime "
DISTRO_FEATURES_append = " automount "

IMAGE_INSTALL_append = " screen mc "

## It is just demo integration
DISTRO_FEATURES_append_m3ulcb = " virtual-display gst-record agl-mapviewer-demo"

# for M3 only
IMAGE_INSTALL_append_m3ulcb = " kernel-module-mmngr-4.9.0-yocto-standard kernel-module-mmngr"
IMAGE_INSTALL_append_m3ulcb = " kernel-module-mmngrbuf-4.9.0-yocto-standard kernel-module-mmngrbuf"
IMAGE_INSTALL_append_m3ulcb = " kernel-module-vspm-if-4.9.0-yocto-standard kernel-module-vspm-if"

