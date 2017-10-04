DESCRIPTION = "Build USB driver for MOCCA box"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV = "0.1"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/can-lin/;protocol=https;branch=dab"
S = "${WORKDIR}/git/Usb-Driver"

SRCREV = "b330168fd3e8043795ab2b9574be12f91ddf0661"
SRCREV_dab = "refs/tags/${DISTRO_BRANCH_VERSION_TAG}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

do_install_append () {
    # modprobe automatically at boot
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${sysconfdir}/modules-load.d
        echo "mocca_usb" > ${D}${sysconfdir}/modules-load.d/mocca_usb.conf
    fi
}
