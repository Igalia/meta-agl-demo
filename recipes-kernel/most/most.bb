DESCRIPTION = "Build MOST driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV = "0.1"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/most;protocol=https;branch=master"

S = "${WORKDIR}/git/driver"
SRCREV = "794e6dc552e626eb6dd506baf941873414d9ef73"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

do_install_append () {
    # modprobe automatically at boot
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${sysconfdir}/modules-load.d
        echo "aim_cdev" > ${D}${sysconfdir}/modules-load.d/aim_cdev.conf
        echo "aim_sound" > ${D}${sysconfdir}/modules-load.d/aim_sound.conf
        echo "aim_network" > ${D}${sysconfdir}/modules-load.d/aim_network.conf
        echo "aim_v4l2" > ${D}${sysconfdir}/modules-load.d/aim_v4l2.conf
        echo "hdm_i2c" > ${D}${sysconfdir}/modules-load.d/hdm_i2c.conf
        echo "hdm_dim2" > ${D}${sysconfdir}/modules-load.d/hdm_dim2.conf
        echo "hdm_usb" > ${D}${sysconfdir}/modules-load.d/hdm_usb.conf
        echo "mostcore" > ${D}${sysconfdir}/modules-load.d/mostcore.conf
    fi
}
