SUMMARY     = "Google noto emoji font pack"
HOMEPAGE    = "https://github.com/googlefonts/noto-emoji"
SECTION     = "fonts"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/googlefonts/noto-emoji;protocol=https;branch=master"
SRCREV = "833a43d03246a9325e748a2d783006454d76ff66"

RDEPENDS_${PN} = "fontconfig"

S = "${WORKDIR}/git"

do_install() {
    install -m 0755 -d ${D}/${datadir}/fonts/ttf
    install -m 0644 -p ${S}/NotoColorEmoji.ttf ${D}/${datadir}/fonts/ttf
}

FILES_${PN} += "${datadir}/fonts/ttf/NotoColorEmoji.ttf"

inherit allarch fontcache

do_configure[noexec] = "1"
do_compile[noexec] = "1"
