DESCRIPTION = "AGL Demo Platform image currently contains a simple HMI and \
demos."

LICENSE = "MIT"

require agl-demo-platform-html5.inc

IMAGE_INSTALL_append = "\
    packagegroup-agl-demo-platform-html5 \
    "
