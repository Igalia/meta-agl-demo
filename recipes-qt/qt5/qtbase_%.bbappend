FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS_append_koelsch = " libegl"
DEPENDS_append_porter = " libegl"

PACKAGECONFIG_GL = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"
PACKAGECONFIG_append = " icu accessibility eglfs"
PACKAGECONFIG_append_rpi = " fontconfig"

EXTRA_OECONF_append = ""


PACKAGECONFIG[gles2]="-opengl es2 -eglfs,,virtual/libgles2 virtual/egl"
