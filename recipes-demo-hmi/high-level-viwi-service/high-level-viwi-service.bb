SUMMARY     = "High level ViWi service"
DESCRIPTION = "AGL High Level service using ViWi protocol to expose CAN API."
HOMEPAGE    = "https://github.com/iotbzh/high-level-viwi-service"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5de84541278ea4e62cacfdc0f890c459"

SRC_URI = "gitsm://github.com/iotbzh/high-level-viwi-service.git;protocol=https"
SRCREV  = "c4935e58769966a9b79b21c45798ab8828fe0ad0"

PV = "4.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# Run-time dependencies
RDEPENDS_${PN} += "low-level-can-service"

inherit cmake aglwgt

AGLWGT_AUTOINSTALL_${PN} := "0"
