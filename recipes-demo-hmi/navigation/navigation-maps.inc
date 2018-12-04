DEPENDS = "tar-native"

SRC_URI_append = ";downloadfilename=${BP}.tar.gz;unpack=0"

do_compile[noexec] = "1"

do_install () {
    install -d ${D}${localstatedir}/mapdata
    tar -C ${D}${localstatedir}/mapdata --no-same-owner -xf ${WORKDIR}/${BP}.tar.gz
}

FILES_${PN} += "${localstatedir}/mapdata/*"

RDEPENDS_${PN} += "navigation"
