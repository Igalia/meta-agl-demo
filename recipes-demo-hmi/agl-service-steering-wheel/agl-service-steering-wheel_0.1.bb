DESCRIPTION = "logitech g29 wheel service"
HOMEPAGE = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/agl-service-steering-wheel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"
SECTION = "apps"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/agl-service-steering-wheel;protocol=http;branch=master"
SRCREV = "4ff6418f145dd4af3a1f5f8b6eba29b958a89521"

PN          = "agl-service-steering-wheel"
S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 af-binder"

inherit cmake aglwgt
