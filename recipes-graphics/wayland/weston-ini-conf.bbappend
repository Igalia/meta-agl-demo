FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#
SRC_URI += "${@bb.utils.contains("DISTRO_FEATURES", "virtual-display", "file://virtualoutput.cfg", "",d)}"

do_configure_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'virtual-display', 'true', 'false', d)}; then
        echo virtual=1 >> ${WORKDIR}/core.cfg
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'gst-record', 'true', 'false', d)}; then
	echo recorder=true >> ${WORKDIR}/virtualoutput.cfg
    fi
}

