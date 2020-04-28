DESCRIPTION = "AGL Demo Platform image currently contains a simple HMI and \
demos."

require agl-demo-platform.inc

LICENSE = "MIT"

# Always include the test widgets
IMAGE_FEATURES_append = " agl-test-wgt"

# add packages for demo platform (include demo apps) here
IMAGE_INSTALL_append = " \
    packagegroup-agl-demo-platform \
    "

