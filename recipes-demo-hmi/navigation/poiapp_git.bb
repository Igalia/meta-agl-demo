SUMMARY     = "AGL Reference POI application."
DESCRIPTION = "This application provides the function of destination search to AGL.  It uses the API provided by AGL Reference Navigation.  This application uses yelp WebAPI."
HOMEPAGE    = "https://github.com/AGLExport/genivi-navi-yelp-client"
SECTION     = "apps"

LICENSE          = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=947b2d60ca3872e172034438e9801200"

inherit cmake_qt5 pkgconfig
inherit aglwgt

DEPENDS += " qtbase libdbus-c++ json-c \
           "

SRCREV  = "b0e059007de776450c91c983a2bcac20414617e9"
SRC_URI = "git://github.com/AGLExport/genivi-navi-yelp-client.git;branch=agl \
          "

S = "${WORKDIR}/git"

