FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

# QT_MODULE_BRANCH = "5.4"

# TODO:
#  These patches for IVI-SHELL are tempolary disabled because of issues. And new
#  patches are proposed.
#
#    file://0020-Add-IVI-Shell-protocol-file-version-patch-v6.patch \
#    file://0021-Implement-initial-IVI-Shell-support.patch \
#    file://0001-protocol-update-3rd-party-ivi-application-protocol.patch \
#    file://0002-qwaylandwindow-add-support-for-IVI-Surface-ID-proper.patch \
#
#  The xdg-shell merged into upstream, so we don't need these patch anymore.
#  But xdg-shell doesn't work well in current AGL Distro because of
#  mismatch of protocol versions between server(weston) and client(Qt Apps).
#
#    file://0016-xdg-shell-Add-xdg-shell-protocol-file-version-1.4.0.patch \
#    file://0017-xdg-shell-Add-minimize-feature-to-QWindow-using-wayl.patch \
#    file://0019-xdg-shell-upgrade-to-support-current-version-weston-.patch \
#

SRC_URI_append = "\
    file://0010-Added-manifest-file-according-to-smack-3-domain-mode.patch \
    "
SRC_URI_append_checkforkrogoth = "\
    file://disable_xcomposite_egl_qt_wayland_client_buffer_integration.patch \
    file://0001-Implement-initial-IVI-shell-support-with-shell-integ.patch \
    file://0002-Fix-multiple-QWindow.patch \
    "


DEPENDS_append_koelsch = " libegl gles-user-module"
DEPENDS_append_porter = " ${@base_conditional('PREFERRED_PROVIDER_virtual/egl', 'mesa', 'mesa', 'libegl gles-user-module', d)}"

SRC_URI_append = " file://0099_qtwayland_no_evdev.patch "
