FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = "\
    file://0001-weston-patch-for-wl-shell-emulator.patch \
    "
