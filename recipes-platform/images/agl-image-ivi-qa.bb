SUMMARY = "A basic system of AGL distribution of IVI profile for Quality Assurance(QA)"

DESCRIPTION = "A basic set of AGL Distribution. This image also has additional \
packages (e.g. commandline tools) for Quality Assurance(QA)."

require agl-image-ivi.bb

LICENSE = "MIT"

IMAGE_INSTALL_append = " \
    packagegroup-agl-test \
    packagegroup-ivi-common-test \
    "

