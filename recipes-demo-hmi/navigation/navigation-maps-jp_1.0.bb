SUMMARY = "AGL Reference Navigation application Japan maps"
DESCRIPTION = "Preload the Japanese maps for the AGL Navigation application."
HOMEPAGE = "http://agl.wismobi.com/"
SECTION = "apps"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

SRC_URI = "http://agl.wismobi.com/data/japan_TR9/navi_data.tar.gz"
SRC_URI[md5sum] = "4fd44b0633d44d41c07227d086cd299c"
SRC_URI[sha256sum] = "ce39a36741baccd6b40277acb8c81ebc181997c75483dffb46ccd22f7877295a"

require navigation-maps.inc

RCONFLICTS_${PN} = "navigation-maps-uk"
