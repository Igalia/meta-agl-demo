FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

QT_MODULE_BRANCH = "5.4"

SRC_URI_append = "\
    file://0010-Added-manifest-file-according-to-smack-3-domain-mode.patch \
    file://0016-xdg-shell-Add-xdg-shell-protocol-file-version-1.4.0.patch \
    file://0017-xdg-shell-Add-minimize-feature-to-QWindow-using-wayl.patch \
    file://0019-xdg-shell-upgrade-to-support-current-version-weston-.patch \
    file://0020-Add-IVI-Shell-protocol-file-version-patch-v6.patch \
    file://0021-Implement-initial-IVI-Shell-support.patch \
    file://disable_xcomposite_egl_qt_wayland_client_buffer_integration.patch \
    file://0001-protocol-update-3rd-party-ivi-application-protocol.patch \
    file://0002-qwaylandwindow-add-support-for-IVI-Surface-ID-proper.patch \
    "

DEPENDS_append_koelsch = " libegl gles-user-module"
DEPENDS_append_porter = " libegl gles-user-module"
