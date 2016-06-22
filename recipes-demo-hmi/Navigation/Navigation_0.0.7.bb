LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://LICENSE;md5=3595e9c703a847d990664d2b396a9df0 \
                  file://COPYING;md5=947b2d60ca3872e172034438e9801200"

DEPENDS =        "glib-2.0 freetype sqlite3 wayland zlib expat openssl virtual/libgles2"
RDEPENDS_${PN} = "glib-2.0 freetype sqlite3 wayland zlib expat openssl libegl libwayland-egl"

SRCREV="v0.0.7"
SRC_URI="git://github.com/gpsnavi/gpsnavi.git"

# To avoid C++ library link failure
SECURITY_CFLAGS = ""

S = "${WORKDIR}/git"

inherit autotools pkgconfig
