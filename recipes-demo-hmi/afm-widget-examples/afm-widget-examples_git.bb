SUMMARY     = "Examples of HTML5 widgets"
DESCRIPTION = "Some HTML5 games"
HOMEPAGE    = "https://github.com/iotbzh/afm-widget-examples"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/iotbzh/afm-widget-examples.git;protocol=https;branch=master"
SRCREV  = "3bc3930c8f4f60d8f79100821502e743d7944f59"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies

SRC_URI += "file://xx-install"

RDEPENDS_afm-widget-examples = "bash virtual/webruntime"

do_install() {
	install -d ${D}/${sysconfdir}/agl-postinsts
	install -m 0755 ${B}/../xx-install ${D}/${sysconfdir}/agl-postinsts
	install -d ${D}/usr/AGL/apps/autoinstall
	for n in annex memory-match rabbit; do
		install -m 0644 ${B}/$n.wgt ${D}/usr/AGL/apps/autoinstall
		ln -s xx-install ${D}/${sysconfdir}/agl-postinsts/11-$n.sh
	done
}

FILES_${PN} += "/usr/AGL/apps/autoinstall \
    ${sysconfdir}/agl-postinsts \
    "


