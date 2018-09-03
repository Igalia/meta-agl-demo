SUMMARY     = "Homescreen binding and client library for application"
DESCRIPTION = "agl-service-homescreen is the binding library"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/apps/agl-service-homescreen"
SECTION     = "HMI"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

DEPENDS = "dbus glib-2.0 af-binder json-c"

inherit cmake aglwgt

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/agl-service-homescreen;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "67962d9837ea37b10c2cf906cbde575cce18ab4b"
S = "${WORKDIR}/git"
