SUMMARY     = "Bluetooth Service Binding"
DESCRIPTION = "AGL Bluetooth Service Binding"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/agl-service-bluetooth"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/agl-service-bluetooth;protocol=http;branch=dab"
SRCREV = "3587e397424285df38d28b1572ce9118724d5122"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# for historical reasons, TODO: migrate to cmake
DEPENDS += "qtbase"

inherit qmake5 aglwgt
