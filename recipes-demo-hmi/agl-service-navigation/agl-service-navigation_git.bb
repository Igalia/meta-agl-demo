SUMMARY     = "Navigation Service Binding"
DESCRIPTION = "AGL Navigation Service API Binding"
HOMEPAGE    = "https://github.com/AGLExport/agl-service-navigation"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://github.com/AGLExport/agl-service-navigation;protocol=git;branch=master"
SRCREV  = "1f1ffc92fcc882aa5e885badbc91a3384f5d77b1"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

DEPENDS = " json-c libdbus-c++ "

inherit cmake aglwgt pkgconfig
