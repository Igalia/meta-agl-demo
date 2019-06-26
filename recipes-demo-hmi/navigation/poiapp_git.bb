SUMMARY     = "AGL Reference POI application."
DESCRIPTION = "This application provides the function of destination search to AGL.  It uses the API provided by AGL Reference Navigation.  This application uses yelp WebAPI."
HOMEPAGE    = "https://github.com/AGLExport/genivi-navi-yelp-client"
SECTION     = "apps"

LICENSE          = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=947b2d60ca3872e172034438e9801200"

inherit cmake_qt5 pkgconfig
inherit aglwgt

DEPENDS += " qtbase json-c libnaviapi-agl \
             af-binder qlibwindowmanager libhomescreen \
           "

RDEPENDS_${PN} =  " qtbase \
                  "

SRCREV = "c2691cb265d9198542482a860f1df378e8c2708b"
SRC_URI = "git://github.com/AGLExport/genivi-navi-yelp-client.git;branch=agl \
           file://0001-add-display-permission.patch \
          "

S = "${WORKDIR}/git"

