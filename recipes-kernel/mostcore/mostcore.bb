DESCRIPTION = "Example of how to build an external Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
#LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
#		   "  

inherit module

PV = "0.1"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/most;protocol=https"

S = "${WORKDIR}/git/driver/${PN}"
SRCREV = "7850efa9077fa84536e0442c65d39a36e25e39d1"
#SRCREV = "${AUTOREV}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

do_install_append () {
    # modprobe automatically at boot
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${sysconfdir}/modules-load.d
        echo "mostcore" > ${D}${sysconfdir}/modules-load.d/mostcore.conf
    fi
}
