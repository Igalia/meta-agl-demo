SUMMARY     = "HVAC for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating HVAC on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/hvac"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/hvac;protocol=http \
           file://0001-Remove-reversal-of-fanspeed-hope-this-is-it.patch \
           "
SRCREV  = "${AUTOREV}"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2"

inherit qmake5 aglwgt

#do_patch_append() {
#
#sed -i -e 's#hvac_values[3].value.*0xFF#hvac_values[3].value#g' binding/hvac-demo-binding.c
#
#}