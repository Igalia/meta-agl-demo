SUMMARY     = "Navigation demo based on Qt examples"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/navigation"
SECTION     = "apps"

LICENSE     = "BSD-3-Clause & CC0v1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b6bbd37d85cd2b68082aa7be27853da1 \
                    file://LICENSE.for_car_png;md5=65d3616852dbf7b1a6d4b53b00626032 \
                   "

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/navigation;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AGL_APP_REVISION}"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

RPROVIDES_${PN} = "virtual/navigation"

DEPENDS  += "qtquickcontrols2 \
             virtual/libhomescreen \
             qlibwindowmanager \
             qtvirtualkeyboard \
             qtlocation \
            "

RDEPENDS_${PN} = " \
             agl-service-gps \
             agl-service-geoclue \
             agl-service-geofence \
            "

inherit qmake5 aglwgt
