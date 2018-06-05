SUMMARY = "Wayland IVI Extension"
DESCRIPTION = "GENIVI Layer Management API based on Wayland IVI Extension"
HOMEPAGE = "http://projects.genivi.org/wayland-ivi-extension"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Wayland%20IVI%20Extension"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1f1a56bb2dadf5f2be8eb342acf4ed79"

SRCREV = "ede33c1c898eeaf673dd2c275e92763a5aabd066"
SRC_URI = "git://github.com/GENIVI/${BPN}.git;protocol=http \
	file://0001-simple-id-agent-initial-commit.patch;patch=1 \
	file://0002-ivi-controller-load-id-agent-module.patch;patch=1 \
        file://0003-ivi-controller-add-resize-setting-suit-to-surface-si.patch \
        file://0001-Resolve-weston-crush-when-repeat-touching-very-quick.patch \
"
S = "${WORKDIR}/git"

DEPENDS = "weston virtual/libgles2 pixman wayland-native"

inherit cmake

EXTRA_OECMAKE := "-DWITH_ILM_INPUT=1"

FILES_${PN} += "${libdir}/weston/*"
FILES_${PN}-dbg += "${libdir}/weston/.debug/*"

EXTRA_OECMAKE += "-DLIB_SUFFIX=${@d.getVar('baselib', True).replace('lib', '')}"

FILESEXTRAPATHS_prepend := ":${THISDIR}/wayland-ivi-extension:"

SRC_URI_append_wandboard = " file://wandboard_fix_build.patch"

# workaround paralellism issue:
PARALLEL_MAKE = ""
