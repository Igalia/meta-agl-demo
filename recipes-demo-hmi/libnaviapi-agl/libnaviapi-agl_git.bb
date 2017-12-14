SUMMARY     = "AGL Navigation API Library"
DESCRIPTION = "AGL Navigation API ver 0.1.0 library for C++"
HOMEPAGE    = "https://github.com/AGLExport/agl-service-navigation"
LICENSE     = "Apache-2.0"
SECTION     = "libs"

LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

DEPENDS = "af-binder json-c libdbus-c++ af-main-native"

inherit cmake

SRC_URI = "git://github.com/AGLExport/agl-service-navigation;protocol=git;branch=master"
SRCREV  = "1f1ffc92fcc882aa5e885badbc91a3384f5d77b1"
S = "${WORKDIR}/git"

