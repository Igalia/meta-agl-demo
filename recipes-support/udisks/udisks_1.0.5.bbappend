SRC_URI += "file://org.freedesktop.UDisks.conf \
            file://udisks.service \
           "
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SYSTEMD_AUTO_ENABLE = "enable"

do_install_append () {
    install -d ${D}${sysconfdir}/dbus-1/system.d
    install -m 644 ${WORKDIR}/org.freedesktop.UDisks.conf ${D}${sysconfdir}/dbus-1/system.d/

    install -d ${D}${base_libdir}/systemd/system
    install -m 0644 ${WORKDIR}/udisks.service ${D}${base_libdir}/systemd/system
}
