FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS_append_koelsch = " libegl"
DEPENDS_append_porter = " libegl"

PACKAGECONFIG_WAYLAND = "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"
PACKAGECONFIG_GL = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"
PACKAGECONFIG_append = " ${PACKAGECONFIG_WAYLAND} icu accessibility"
PACKAGECONFIG_append = " fontconfig"

EXTRA_OECONF_append = ""

# '-qpa wayland-egl' set wayland-egl as default of platform plagins
PACKAGECONFIG[wayland]="-qpa wayland-egl -no-qpa-platform-guard"
