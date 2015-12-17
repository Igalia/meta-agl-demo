FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "\
    file://0001-Force-qdbus-to-make-introspect-for-every-findMetaObj.patch \
    "

DEPENDS_append_koelsch = " libegl"
DEPENDS_append_porter = " libegl"

PACKAGECONFIG_GL = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"
PACKAGECONFIG_append = " icu accessibility"

EXTRA_OECONF_append = " -qpa wayland"
