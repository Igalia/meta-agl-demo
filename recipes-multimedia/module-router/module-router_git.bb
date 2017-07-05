DESCRIPTION = "Pulseaudio router module"
HOMEPAGE = "https://github.com/tisogai/module-router"
LICENSE = "LGPLv2"
SECTION = "multimedia"
DEPENDS = "pulseaudio"

LIC_FILES_CHKSUM = "file://LICENSE;md5=fc178bcd425090939a8b634d1d6a9594"

SRC_URI = " \
    git://github.com/tisogai/module-router.git;protocol=https;branch=master \
  "

SRCREV = "8104a3174e6f54ace53066ab1797d9b9bd2bdf63"

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


