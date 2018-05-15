SUMMARY = "Wayland IVI Extension"
DESCRIPTION = "GENIVI Layer Management API based on Wayland IVI Extension"
HOMEPAGE = "http://projects.genivi.org/wayland-ivi-extension"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Wayland%20IVI%20Extension"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1f1a56bb2dadf5f2be8eb342acf4ed79"

SRCREV = "ff067a640451de46836200624ece7ac777977c30"
SRC_URI = "git://github.com/GENIVI/${BPN}.git;branch=2.0;protocol=http \
    "
SRC_URI_append_wandboard = " file://wandboard_fix_build.patch"

S = "${WORKDIR}/git"

DEPENDS = "weston virtual/libgles2 pixman wayland-native"

FILESEXTRAPATHS_prepend := ":${THISDIR}/wayland-ivi-extension:"

# workaround paralellism issue:
PARALLEL_MAKE = ""

inherit cmake

EXTRA_OECMAKE := "-DWITH_ILM_INPUT=1"

FILES_${PN} += "${libdir}/weston/*"
FILES_${PN} += "${datadir}/wayland-protocols/stable/ivi-application/*"

FILES_${PN}-dbg += "${libdir}/weston/.debug/*"

EXTRA_OECMAKE += "-DLIB_SUFFIX=${@d.getVar('baselib', True).replace('lib', '')}"
