# add missing dependencies for SDK

RDEPENDS_${PN} += " \
	qttools-plugins \
	qtquickcontrols-dev \
	qtquickcontrols-qmlplugins \
	qtquickcontrols2-dev \
	qtquickcontrols2-mkspecs \
	qtquickcontrols2-qmlplugins \
"

# remove dependency on qtwebkit (still added to SDK by packagegroup-qt5-toolchain-target)
# see SPEC-1159
RDEPENDS_${PN}_remove = " \
    qtwebkit-dev \
    qtwebkit-mkspecs \
    qtwebkit-qmlplugins \
"

# add QtAGLExtras
RDEPENDS_${PN} += " \
    ${@bb.utils.contains("DISTRO_FEATURES", "agl-hmi-framework", " qtaglextras-dev qtaglextras-mkspecs", "",d)} \
"
