FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://10-unload-modules.pa"

# Add .include directive to default.pa so optional configuration can be added
do_install_append () {
    echo ".include ${sysconfdir}/pulse/default.d" >> ${D}${sysconfdir}/pulse/default.pa
    install -d ${D}${sysconfdir}/pulse/default.d
    install -m 0644 ${WORKDIR}/10-unload-modules.pa ${D}${sysconfdir}/pulse/default.d/
}
