SRC_URI += "file://org.freedesktop.UDisks.conf \
            file://udisks.service \
            file://automount.service \
            file://automount.sh \
           "
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

FILES_${PN} += "${base_libdir}/systemd/system/automount.service \
                ${base_libdir}/systemd/system/udisks.service \
                ${libexecdir}/automount.sh \
               "

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "udisks.service automount.service"

do_install_append () {
    install -d ${D}${sysconfdir}/dbus-1/system.d
    install -m 644 ${WORKDIR}/org.freedesktop.UDisks.conf ${D}${sysconfdir}/dbus-1/system.d/

    install -d ${D}${base_libdir}/systemd/system
    install -m 0644 ${WORKDIR}/udisks.service ${D}${base_libdir}/systemd/system

    install -d ${D}${base_libdir}/systemd/system
    install -m 0644 ${WORKDIR}/automount.service ${D}${base_libdir}/systemd/system

    install -d ${D}${libexecdir}
    install -m 0755 ${WORKDIR}/automount.sh ${D}${libexecdir}/automount.sh
}
