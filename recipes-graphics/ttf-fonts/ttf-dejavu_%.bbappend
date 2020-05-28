FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
DEPENDS += "fontforge-native"

# From the orginal work located at https://aur.archlinux.org/packages/ttf-dejavu-emojiless/

SRC_URI_append = " file://cleaner.py"

do_install_prepend() {
    for TTF in ${S}/*.ttf; do
        fontforge -script ${WORKDIR}/cleaner.py ${TTF}
    done
}
