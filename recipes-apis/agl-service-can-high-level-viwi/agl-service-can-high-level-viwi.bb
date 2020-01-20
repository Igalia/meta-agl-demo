SUMMARY     = "High level ViWi service"
DESCRIPTION = "AGL High Level service using ViWi protocol to expose CAN API."
HOMEPAGE    = "https://git.automotivelinux.org/apps/agl-service-can-high-level-viwi/"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5de84541278ea4e62cacfdc0f890c459"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/agl-service-can-high-level-viwi;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AGL_APP_REVISION}"

PV = "4.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# Run-time dependencies
RDEPENDS_${PN} += "agl-service-can-low-level"

inherit cmake aglwgt

AGLWGT_AUTOINSTALL_${PN} := "0"
