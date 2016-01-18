FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = "\
    file://0001-weston-patch-for-wl-shell-emulator.patch \
    file://0001-ivi-shell-Send-keyboard-events-to-clients-tha-are-bi.patch \
    "
