SUMMARY = "hvacplugin"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
                    file://hvacplugin.cpp;startline=4;endline=11;md5=5adb69b6702cd5745961047a30ef560a"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/hvacplugin;protocol=https \
           file://hvacplugin \
          "
SRCREV = "00cb8ff4084d635d3a929cb41b8d853f6c4baf76"
S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 json-c automotive-message-broker"
RDEPENDS_{PN} = "automotive-message-broker"

inherit cmake pkgconfig

EXTRA_OECMAKE += "-DPLUGIN_INSTALL_PATH=${libdir}/automotive-message-broker"

do_install_append() {
       install -Dm 0644 ${WORKDIR}/hvacplugin ${D}/${sysconfdir}/ambd/plugins.d/hvacplugin
}

FILES_${PN} += "${libdir}/automotive-message-broker/*.so"
FILES_${PN}-dbg += "${libdir}/automotive-message-broker/.debug"

