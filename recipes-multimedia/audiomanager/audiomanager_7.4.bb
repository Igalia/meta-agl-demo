DESCRIPTION = "GENIVI Audio Manager"
HOMEPAGE = "http://projects.genivi.org/audio-manager/home"
LICENSE = "MPLv2"
SECTION = "multimedia"
DEPENDS = "dbus dlt-daemon systemd"
LIC_FILES_CHKSUM = "file://LICENCE;md5=f164349b56ed530a6642e9b9f244eec5"

SRC_URI = " \
    git://github.com/GENIVI/AudioManager.git;protocol=https;branch=master \
    file://audiomanager.service \
"
RDEPENDS_${PN} = "audiomanager-plugins module-router"
SRCREV = "daf851ee7a41d1b0572c0c95e15f61e427ce97f1"

S = "${WORKDIR}/git"
inherit cmake systemd
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "audiomanager.service"
SYSTEMD_AUTO_ENABLE = "enable"

EXTRA_OECMAKE = " \
    -DWITH_TESTS=OFF \
    -DWITH_DOCUMENTATION=OFF \
    -DWITH_DLT=ON \
    -DWITH_TELNET=OFF \
    -DWITH_SYSTEMD_WATCHDOG=ON \
    -DGLIB_DBUS_TYPES_TOLERANT=ON \
    -DWITH_CAPI_WRAPPER=OFF \
    -DWITH_DBUS_WRAPPER=ON \
    -DWITH_SHARED_UTILITIES=ON \
    -DWITH_SHARED_CORE=ON \
"

FILES_${PN} = " \
    ${bindir}/AudioManager \
    ${libdir}/libAudioManagerCore.so.7 \
    ${libdir}/libAudioManagerCore.so.7.4 \
    ${libdir}/libAudioManagerCore.so.7.4.12 \
    ${libdir}/libAudioManagerUtilities.so.7 \
    ${libdir}/libAudioManagerUtilities.so.7.4 \
    ${libdir}/libAudioManagerUtilities.so.7.4.12 \
    ${systemd_unitdir}/audiomanager.service \
"

FILES_${PN}-dev = " \
    ${includedir}/audiomanager/* \
    ${libdir}/cmake/* \
    ${libdir}/pkgconfig/* \
    ${libdir}/libAudioManagerCore.so \
    ${libdir}/libAudioManagerUtilities.so \
"

do_install_append() {
    install -d  ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/audiomanager.service ${D}${systemd_unitdir}/system/
}
