SUMMARY     = "AGL Navigation API Library"
DESCRIPTION = "AGL Navigation API ver 0.1.0 library for C++"
HOMEPAGE    = "https://github.com/AGLExport/agl-service-navigation"
LICENSE     = "Apache-2.0"
SECTION     = "libs"

LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

DEPENDS = "af-binder json-c libdbus-c++ af-main-native"

inherit cmake

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/agl-service-navigation;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AGL_APP_REVISION}"
S = "${WORKDIR}/git"

# FIXME: Remove once CMake+ninja issues are resolved
OECMAKE_GENERATOR = "Unix Makefiles"
