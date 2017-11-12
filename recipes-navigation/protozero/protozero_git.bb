SUMMARY     = "protozero library"
DESCRIPTION = "Minimalist protocol buffer decoder and encoder in C++"

HOMEPAGE    = "https://github.com/mapbox/protozero"
SECTION     = "apps"

LICENSE     = "BSD & Apache-2.0"
LIC_FILES_CHKSUM = "\
    file://LICENSE.md;md5=bb1a27fdd409f635a3f92106ef78d30a \
    file://LICENSE.from_folly;md5=2ee41112a44fe7014dce33e26468ba93 \
    "

SRC_URI = "git://github.com/mapbox/protozero.git;protocol=http"
SRCREV  = "aa8b304cf63831589f52c254b5af2c688bdc2fc4"

S  = "${WORKDIR}/git"

CFLAGS_prepend = " -I${STAGING_DIR_HOST}/include"

inherit cmake pkgconfig
