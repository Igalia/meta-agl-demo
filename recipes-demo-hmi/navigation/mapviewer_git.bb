SUMMARY     = "AGL meter demo application."
DESCRIPTION = "This application provides the function of Meter cluster to AGL. "
HOMEPAGE    = "https://github.com/AGLExport/mapviwer"
SECTION     = "apps"

LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://LICENSE;md5=3595e9c703a847d990664d2b396a9df0 \
                  file://COPYING;md5=947b2d60ca3872e172034438e9801200"

DEPENDS = " \
          glib-2.0 freetype sqlite3 wayland zlib expat openssl virtual/libgles2 virtual/libgl virtual/egl \
          wayland \
          "

RDEPENDS_${PN} = " navigation "

SRCREV="2dfef5e9141ccb11f83d141c6a7dcbe0ecd49e84"
SRC_URI="git://github.com/AGLExport/mapviwer.git"

# To avoid C++ library link failure
SECURITY_CFLAGS = ""

inherit autotools pkgconfig

S = "${WORKDIR}/git"

