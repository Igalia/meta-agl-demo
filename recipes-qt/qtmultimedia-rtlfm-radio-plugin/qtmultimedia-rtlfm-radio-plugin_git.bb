SUMMARY = "QtMultimedia RTL-SDR Radio Plugin"
DESCRIPTION = "QtMultimedia RTL-SDR Radio Plugin"
HOMEPAGE = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/staging/qtmultimedia-rtlfm-radio-plugin"
LICENSE = "MPL-2.0 & GPLv2+"
LIC_FILES_CHKSUM = "file://rtlfmradioplugin.cpp;endline=5;md5=747a800420eac5cb6ab2cb85514d88b2 \
                    file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "qtmultimedia pulseaudio alsa-lib rtl-sdr"

PV = "0.1+git${SRCPV}"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/staging/qtmultimedia-rtlfm-radio-plugin;protocol=https"
SRCREV = "${AUTOREV}"

# Pinned branch/SRCREV for Charming Chinook
SRC_URI_chinook = "git://gerrit.automotivelinux.org/gerrit/staging/qtmultimedia-rtlfm-radio-plugin;protocol=https;branch=chinook"
SRCREV_chinook = "5d01b91533af2ba8ffd7afce48b8296e14d60e55"

S = "${WORKDIR}/git"

inherit qmake5

FILES_${PN} += "${OE_QMAKE_PATH_PLUGINS}/mediaservice/*.so"
FILES_${PN}-dbg += "${OE_QMAKE_PATH_PLUGINS}/mediaservice/.debug"

