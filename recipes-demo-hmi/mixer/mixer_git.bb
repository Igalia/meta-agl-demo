SUMMARY     = "Mixer for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI application for control of PulseAudio mixer elements"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/mixer"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/mixer;protocol=https;branch=${AGL_BRANCH}"

# 'legacy' version (eel_5.0.3 == master for CES'18) when 4A is not enabled
SRCREV  = "d9bb450ee8898cb810027897a32afd3adcb05d9f"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2 \
            pulseaudio \
            virtual/libhomescreen \
            qlibwindowmanager \
"

RPROVIDES_${PN} += "virtual/mixer"

inherit qmake5 aglwgt
