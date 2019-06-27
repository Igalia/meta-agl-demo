SUMMARY     = "AGL Reference On Demand Navigation application."
DESCRIPTION = "This application provides the function of Navigation to AGL. "
HOMEPAGE    = "https://github.com/YoshitoMomiyama/aglqtnavigation.git"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

inherit qmake5 aglwgt pkgconfig

SRC_URI = "git://github.com/YoshitoMomiyama/aglqtnavigation.git;branch=master \
           file://0001-switch-to-alsa-output.patch \
           file://0002-update-permissions.patch \
           file://org.agl.naviapi.conf \
           "
SRCREV  = "a6930c2dff988e45e18f91a2368d829c08942b30"

DEPENDS += " qtbase qtquickcontrols2 \
             qlibhomescreen \
             qlibwindowmanager \
             qtlocation qtaglextras \
           "

RDEPENDS_${PN} += " qtlocation \
                    flite \
                    openjtalk \
                    gstreamer1.0 \
                    ondemandnavi-config \
                  "

RPROVIDES_${PN} = "virtual/navigation"

S = "${WORKDIR}/git"

do_install_append() {
   install -d ${D}/etc/dbus-1/session.d/
   install -m 0644 ${WORKDIR}/org.agl.naviapi.conf ${D}/etc/dbus-1/session.d/
}

PATH_prepend = "${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_QT_BINS}:"

