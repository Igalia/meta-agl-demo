SUMMARY     = "libosmium library"
DESCRIPTION = "Fast and flexible C++ library for working with OpenStreetMap data. "
HOMEPAGE    = "http://osmcode.org/libosmium"
SECTION     = "apps"

LICENSE     = "BSL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e4224ccaecb14d942c71d31bef20d78c"

SRC_URI = "git://github.com/osmcode/libosmium.git;protocol=http"
SRCREV  = "28c676054064c6b75204f0981c21fb3e265c9319"

S  = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DBUILD_EXAMPLES=OFF -DBUILD_TESTING=OFF"

DEPENDS = "boost protozero"

inherit cmake pkgconfig
