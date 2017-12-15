SUMMARY     = "agl-service-signal-composer"
DESCRIPTION = "AGL High Level Signaling service to handle CAN, LIN, and others signaling sources"
HOMEPAGE    = "https://git.automotivelinux.org/apps/agl-service-signal-composer/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit cmake pkgconfig aglwgt

SRC_URI = "gitsm://git.automotivelinux.org/apps/agl-service-signal-composer;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "35096ad54a6ab61ef3a74cb85a285ec00c1e2da0"

PV = "4.0-RC5+git${SRCPV}"
S  = "${WORKDIR}/git"

