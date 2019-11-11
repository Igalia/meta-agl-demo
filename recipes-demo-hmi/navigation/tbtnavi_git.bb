SUMMARY     = "AGL Reference Navigation Cluster Streaming application"
DESCRIPTION = "Demo AGL turn by turn cluster navigation application based on QtLocation widget."
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/admin/repos/apps/tbtnavi"
SECTION     = "apps"

LICENSE     = "Apache-2.0 & ISC & BSD-3-Clause & BSL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984 \
                    file://LICENSE.mapbox-cheap-ruler-cpp;md5=761263ee6bdc98e8697d9fbc897021ba \
                    file://LICENSE.mapbox-geometry.hpp;md5=6e44f5d6aeec54f40fc84eebe3c6fc6c \
                    file://LICENSE.mapbox-variant;md5=79558839a9db3e807e4ae6f8cd100c1c \
                    file://include/mapbox/recursive_wrapper.hpp;beginline=4;endline=13;md5=cd3341aae76c0cf8345935abd20f0051 \
"

DEPENDS += "qtbase \
            qtquickcontrols2 \
            qlibhomescreen \
            qlibwindowmanager \
            qtlocation \
            libqtappfw \
"

PV = "1.0+git${SRCPV}"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/tbtnavi;protocol=https;branch=${AGL_BRANCH}"
#SRCREV  = "4a8b7a6301e4b093c99329d0a16fbee6c535f312"
SRCREV = "${AGL_APP_REVISION}"

S = "${WORKDIR}/git"

inherit qmake5 aglwgt pkgconfig

RDEPENDS_${PN} += "qtlocation \
                   ondemandnavi-config \
                   agl-service-navigation \
                   libqtappfw \
"
