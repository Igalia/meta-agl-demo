SUMMARY     = "AGL Reference Navigation application."
DESCRIPTION = "This application provides the function of Navigation to AGL. "
HOMEPAGE    = "http://agl.wismobi.com/"
SECTION     = "apps"

LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://LICENSE;md5=3595e9c703a847d990664d2b396a9df0 \
                  file://COPYING;md5=947b2d60ca3872e172034438e9801200"

DEPENDS = " \
          glib-2.0 freetype sqlite3 wayland zlib expat openssl virtual/libgles2 virtual/libgl virtual/egl \
          wayland libdbus-c++ af-main af-binder libwindowmanager virtual/libhomescreen \
          "

RDEPENDS_${PN} = " flite openjtalk glib-2.0 freetype sqlite3 wayland zlib expat openssl \
                   wayland libdbus-c++ af-main "

RDEPENDS_${PN} += " agl-service-navigation "


SRCREV="bc6dfb823fad71c05f50d7755152c092a6ee5a80"
SRC_URI="git://github.com/insert0003/gpsnavi.git;branch=agl \
         file://download_mapdata_jp.sh \
         file://download_mapdata_uk.sh \
         file://org.agl.naviapi.conf \
"

RPROVIDES_${PN} = "virtual/navigation"

# To avoid C++ library link failure
SECURITY_CFLAGS = ""

inherit autotools pkgconfig
inherit aglwgt

S = "${WORKDIR}/git"

do_install_append() {
# mapdata install scripts
   install -d ${D}/usr/AGL/apps
   install -m 0755 ${WORKDIR}/download_mapdata_jp.sh ${D}/usr/AGL/apps/
   install -m 0755 ${WORKDIR}/download_mapdata_uk.sh ${D}/usr/AGL/apps/

   install -d ${D}/etc/dbus-1/session.d/
   install -m 0644 ${WORKDIR}/org.agl.naviapi.conf ${D}/etc/dbus-1/session.d/

   install -d ${D}/var/mapdata
}

FILES_${PN} += " /usr/AGL/apps/*.sh /var/mapdata "
