SUMMARY = "AGL Reference Navigation application UK maps"
DESCRIPTION = "Preload the UK maps for the AGL Navigation application."
HOMEPAGE = "http://agl.wismobi.com/"
SECTION = "apps"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

SRC_URI = "http://agl.wismobi.com/data/UnitedKingdom_TR9/navi_data_UK.tar.gz"
SRC_URI[md5sum] = "f711c6d2c88553a1de4db9f7e12f6e8e"
SRC_URI[sha256sum] = "515bdc81ac0615d541e0d18c186ad5cd24de2d47b60e13079a918f6dec802fd7"

require navigation-maps.inc

RCONFLICTS_${PN} = "navigation-maps-jp"
