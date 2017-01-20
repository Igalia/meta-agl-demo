SUMMARY = "A full set of AGL Distribution for testing as Quality Assurance"

DESCRIPTION = "A full set of AGL Distribution. This image also has additional \
packages (e.g. commandline tools) for Quality Assurance(QA)."

LICENSE = "MIT"

require agl-demo-platform.bb

IMAGE_INSTALL_append = " \
    packagegroup-ivi-common-test \
    packagegroup-agl-demo-platform-qa \
    "
