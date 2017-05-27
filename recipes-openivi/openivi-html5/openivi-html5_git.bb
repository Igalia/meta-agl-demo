DESCRIPTION = "OpenIVI Mobility HTML5 environment"
HOMEPAGE = "https://openivimobility.github.io/"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4641e94ec96f98fabc56ff9cc48be14b"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
	git://github.com/openivimobility/openivi-html5.git;branch=master;tag=0b370eea8c7c852365bd717013a523b678abbdba \
	file://openivi-html5.sh \
	file://openivi-html5.service \
"

inherit pkgconfig cmake_qt5 externalsrc systemd
PV = "0.1"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "qtbase qtwebkit inputeventmanager windowmanager"
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
    install -d ${D}${systemd_user_unitdir}
    install -p -D ${WORKDIR}/openivi-html5.service ${D}${systemd_user_unitdir}/openivi-html5.service
    install -d ${D}${sysconfdir}/systemd/user/default.target.wants
    ln -sf ${systemd_user_unitdir}/openivi-html5.service ${D}${sysconfdir}/systemd/user/default.target.wants
  fi
}

FILES_${PN} += " ${prefix}/bin/openivi-html5.sh ${systemd_user_unitdir} ${sysconfdir}"
