FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = "\
    file://0001-weston-patch-for-wl-shell-emulator.patch \
    file://0001-ivi-shell-fix-TODO-which-expects-only-one-screen-in-.patch \
    file://0002-ivi-shell-avoid-inserting-a-ivi_layer-to-multiple-sc.patch \
    file://0003-ivi-shell-fix-layout_layer.view_list-is-not-initiliz.patch \
    file://0004-ivi-shell-remove-a-code-which-expects-only-a-screen-.patch \
    file://0005-ivi-shell-multi-screen-support.-ivi_layout_screen-to.patch \
    file://0006-ivi-shell-transforming-from-a-single-screen-coordina.patch \
    file://0007-RFR-ivi-shell-multi-screen-support-to-calcuration-of.patch \
    "
