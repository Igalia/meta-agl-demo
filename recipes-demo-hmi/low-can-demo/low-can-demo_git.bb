SUMMARY     = "Low CAN demo HTML5 UI"
DESCRIPTION = "AGL HTML5 UI to display car metric (speed, rpm, ...) on AGL Distribution"
HOMEPAGE    = "https://github.com/iotbzh/low-can-demo"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "gitsm://github.com/iotbzh/low-can-demo.git;protocol=https"
SRCREV  = "4b325a18b9d2d7906369eeb66044fadc9e1c692a"

PV = "4.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# Run-time dependencies
RDEPENDS_${PN} += " low-level-can-service"

inherit cmake aglwgt

AGLWGT_AUTOINSTALL_${PN} := "0"
