DESCRIPTION = "Pulseaudio router module"
HOMEPAGE = "https://github.com/tisogai/module-router"
LICENSE = "LGPLv2"
SECTION = "multimedia"
DEPENDS = "pulseaudio"

LIC_FILES_CHKSUM = "file://LICENSE;md5=fc178bcd425090939a8b634d1d6a9594"

SRC_URI = " \
    git://github.com/tisogai/module-router.git;protocol=https;branch=master \
  "

SRCREV = "f38273696eecd718c5cfb1285a2cd0d796a0d540"

S = "${WORKDIR}/git"
inherit cmake
OECMAKE_CXX_FLAGS += "-std=c++11"

# NOTE: The specific product should have a separate customtypes.xsd
# and should be appended to FILES_${PN} in product specific
# audiomanager-generic-controller_git.bbappend
FILES_${PN} = " \
    ${libdir}/pulse-*/modules/module-router.so \
"


FILES_${PN}-dbg += " \
    ${libdir}/pulse-*/modules/.debug/module-router.so \
"


