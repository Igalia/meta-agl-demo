SUMMARY     = "Google noto emoji font pack"
HOMEPAGE    = "https://github.com/googlefonts/noto-emoji"
SECTION     = "fonts"

LICENSE     = "OFL-1.1"
LIC_FILES_CHKSUM = "file://${S}/fonts/LICENSE;md5=55719faa0112708e946b820b24b14097"

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
