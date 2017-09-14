FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS_append_koelsch = " libegl"
DEPENDS_append_porter = " libegl"

PACKAGECONFIG_WAYLAND = "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"
PACKAGECONFIG_GL = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"
PACKAGECONFIG_append = " ${PACKAGECONFIG_WAYLAND} icu accessibility"
PACKAGECONFIG_append = " fontconfig"
PACKAGECONFIG_append = " sql-sqlite"

EXTRA_OECONF_append = ""

# '-qpa wayland-egl' set wayland-egl as default of platform plagins
PACKAGECONFIG[wayland]="-qpa wayland-egl -no-qpa-platform-guard"

# Temporarily fix bug due to binutils 2.28
QT_CONFIG_FLAGS_append = " -no-use-gold-linker"

# hotfix for RPM folder clash 
#   file /opt/poky-agl/4.90.0+snapshot/sysroots/x86_64-aglsdk-linux/environment-setup.d conflicts between attempted installs of nativesdk-cmake-3.7.2-r0.x86_64_nativesdk and nativesdk-qtbase-tools-5.8.0+git0+49dc9aa409-r0.x86_64_nativesdk
DIRFILES_class-nativesdk = ""