SUMMARY     = "AGL Reference On Demand Navigation application config."
DESCRIPTION = "Config file for on-demand navigation app."
HOMEPAGE    = "https://github.com/YoshitoMomiyama/aglqtnavigation.git"
SECTION     = "apps"
LICENSE     = "Proprietary"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

SRC_URI = "file://naviconfig.ini \
          "

inherit allarch

MAPBOX_ACCESS_TOKEN ?= "Please set mapbox access token"

do_compile[noexec] = "1"

do_install () {
    install -D -m 644 ${WORKDIR}/naviconfig.ini ${D}${sysconfdir}/naviconfig.ini
    sed -i -e 's/MAPBOX_ACCESS_TOKEN/${MAPBOX_ACCESS_TOKEN}/' ${D}${sysconfdir}/naviconfig.ini
}
