DESCRIPTION = "slLIN driver module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

DEPENDS = "virtual/kernel"

SRC_URI = "git://github.com/trainman419/linux-lin.git;protocol=https"
SRCREV = "155d885e8ccc907a56f6c86c4b159fac27ef6fec"
S = "${WORKDIR}/git/sllin"

PV = "0.1+git${SRCPV}"

SRC_URI_append = " \
	file://0001_update_makefile.patch;pnum=2 \
	file://0002_fix_null_operation_check.patch;pnum=2 \
	file://0003-Allow-recent-kernels-newer-4.11.x-to-build.patch;pnum=2 \
	file://0001-Disable-sllin-driver-debug-log.patch;pnum=2 \
"

KERNEL_MODULE_AUTOLOAD_append = " sllin"
KERNEL_MODULE_PROBECONF_append = " sllin"

SLLINBAUDRATE ??= "9600"
module_conf_sllin = "options sllin baudrate=${SLLINBAUDRATE}"
