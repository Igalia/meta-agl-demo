SUMMARY     = "AGL Reference POI application."
DESCRIPTION = "This application provides the function of destination search to AGL.  It uses the API provided by AGL Reference Navigation.  This application uses yelp WebAPI."
HOMEPAGE    = "https://github.com/AGLExport/genivi-navi-yelp-client"
SECTION     = "apps"

LICENSE          = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=947b2d60ca3872e172034438e9801200"

inherit cmake_qt5 pkgconfig

DEPENDS += " qtbase libdbus-c++ json-c zip-native \
           "

SRCREV="3e162a60c76dee1b4dede81db55e0fa6bae05433"
SRC_URI="git://github.com/AGLExport/genivi-navi-yelp-client.git;branch=agl \
         file://config.xml \
"

S = "${WORKDIR}/git"

do_install_append() {
   mkdir -p ${WORKDIR}/widget
   install -m 0644 ${WORKDIR}/config.xml ${WORKDIR}/widget
   install -m 0755 ${D}/usr/bin/yelp-client ${WORKDIR}/widget/poi
   zip -ju ${WORKDIR}/widget/poi.wgt ${WORKDIR}/widget/poi ${WORKDIR}/widget/config.xml

   install -d ${D}/usr/AGL/apps
   install -m 0644 ${WORKDIR}/widget/poi.wgt ${D}/usr/AGL/apps/
}

FILES_${PN} += " /usr/AGL/apps/poi.wgt "
