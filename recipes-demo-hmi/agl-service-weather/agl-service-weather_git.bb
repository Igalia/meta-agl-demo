SUMMARY     = "Weather Service Binding"
DESCRIPTION = "AGL Weather Service Binding"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/agl-service-weather"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/apps/agl-service-weather;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AGL_APP_REVISION}"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

DEPENDS = "glib-2.0 json-c"
RDEPENDS_${PN} = "agl-service-geoclue"

inherit cmake aglwgt pkgconfig
