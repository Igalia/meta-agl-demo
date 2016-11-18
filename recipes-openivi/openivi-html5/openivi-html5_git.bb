DESCRIPTION = "OpenIVI Mobility HTML5 environment"
HOMEPAGE = "https://openivimobility.github.io/"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4641e94ec96f98fabc56ff9cc48be14b"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
	git://github.com/openivimobility/openivi-html5.git;branch=master;tag=7e5d9574758e504b194d16a52e04a1cfe4320138 \
	file://openivi-html5.sh \
	file://openivi-html5.service \
"

inherit pkgconfig cmake_qt5 externalsrc systemd
PV = "0.1"
PR = "r0"

S = "${WORKDIR}/git"

SYSTEMD_SERVICE_${PN} = "openivi-html5.service"

RDEPENDS_${PN} = "qtbase qtwebkit"
DEPENDS = "qtbase-native qtbase qtwebkit"

EXTRA_OECMAKE = " -DCMAKE_DISABLE_FIND_PACKAGE_X11=TRUE "

FILES_${PN} = "/usr/bin/openivi-html5 /usr/share/openivi/*"

do_install() {
  install -d ${D}${bindir}
  install -m 0755 openivi-html5 ${D}${bindir}

  install -d ${D}${datadir}/openivi/
  cp -r ${S}/example ${D}${datadir}/openivi/

  install -p -D ${WORKDIR}/openivi-html5.sh ${D}${bindir}/openivi-html5.sh

  if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
    install -p -D ${WORKDIR}/openivi-html5.service ${D}${systemd_unitdir}/system/openivi-html5.service
  fi
}

FILES_${PN} += " ${prefix}/bin/openivi-html5.sh"
