LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://LICENSE;md5=3595e9c703a847d990664d2b396a9df0 \
                  file://COPYING;md5=947b2d60ca3872e172034438e9801200"

DEPENDS = " \
          glib-2.0 freetype sqlite3 wayland zlib expat openssl virtual/libgles2 virtual/libgl virtual/egl \
          wayland libdbus-c++ zip-native \
          "

RDEPENDS_${PN} = " flite openjtalk "

SRCREV="1b7218335b5b7a5312e3611138c70c49a27a3b9f"
SRC_URI="git://github.com/AGLExport/gpsnavi.git \
         file://flite.in \
         file://jtalk.in \
         file://config.xml \
         file://download_mapdata.sh \
"

# To avoid C++ library link failure
SECURITY_CFLAGS = ""

inherit autotools pkgconfig

S = "${WORKDIR}/git"

do_compile_prepend() {
   cp ${WORKDIR}/flite.in ${S}/
   cp ${WORKDIR}/jtalk.in ${S}/
}

do_install_append() {
   mkdir -p ${WORKDIR}/widget
   install -m 0644 ${WORKDIR}/config.xml ${WORKDIR}/widget
   install -m 0755 ${D}/usr/bin/navi ${WORKDIR}/widget
   zip -ju ${WORKDIR}/widget/navigation.wgt ${WORKDIR}/widget/navi ${WORKDIR}/widget/config.xml

   install -d ${D}/usr/AGL/apps
   install -m 0644 ${WORKDIR}/widget/navigation.wgt ${D}/usr/AGL/apps/
   install -m 0755 ${WORKDIR}/download_mapdata.sh ${D}/usr/AGL/apps/

   install -d ${D}/var/mapdata
}

FILES_${PN} += " /usr/AGL/apps/* /var/mapdata "
