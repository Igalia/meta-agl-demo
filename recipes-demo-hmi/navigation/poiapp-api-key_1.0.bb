SUMMARY = "AGL Reference POI application API key."
DESCRIPTION = "Preload the API key for the AGL POI application."
HOMEPAGE = "https://github.com/AGLExport/genivi-navi-yelp-client"
SECTION = "apps"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

inherit allarch

do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_compile[noexec] = "1"

POIAPP_CLIENT_ID ?= ""
POIAPP_API_KEY ?= ""

do_install () {
    install -d ${D}${sysconfdir}
    echo "${POIAPP_CLIENT_ID}" > ${D}${sysconfdir}/poikey
    echo "${POIAPP_API_KEY}" >> ${D}${sysconfdir}/poikey
}
