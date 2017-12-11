SUMMARY     = "Navigation Service Binding"
DESCRIPTION = "AGL Navigation Service API Binding"
HOMEPAGE    = "https://github.com/AGLExport/agl-service-navigation"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://github.com/AGLExport/agl-service-navigation;protocol=git;branch=master"
SRCREV  = "b75e80244471e3486176e5ba476989410ecf5bda"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

DEPENDS = " json-c libdbus-c++ "

inherit cmake aglwgt pkgconfig
