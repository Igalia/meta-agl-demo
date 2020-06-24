SUMMARY     = "AGL Reference On Demand Navigation application."
DESCRIPTION = "Demo AGL navigation application based on QtLocation widget."
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/ondemandnavi"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

DEPENDS += "qtquickcontrols2 \
            qlibhomescreen \
            qtlocation \
            libqtappfw \
"

PV = "1.0+git${SRCPV}"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/ondemandnavi;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AGL_APP_REVISION}"

S = "${WORKDIR}/git"

inherit qmake5 aglwgt pkgconfig

RDEPENDS_${PN} += "qtlocation \
                   flite \
                   libqtappfw \
                   openjtalk \
                   gstreamer1.0 \
                   ondemandnavi-config \
"

RPROVIDES_${PN} = "virtual/navigation"

