SUMMARY = "Qt5 for native GUI framework of AGL IVI profile"
DESCRIPTION = "A set of Qt5 packages which required by Native App Fw Subsystem"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-appfw-native-qt5 \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    qtbase \
    qtbase-dev \
    qtbase-fonts \
    qtbase-fonts-pfa \
    qtbase-fonts-pfb \
    qtbase-fonts-qpf \
    qtbase-fonts-ttf-dejavu \
    qtbase-fonts-ttf-vera \
    qtbase-plugins \
    qtbase-staticdev \
    qtbase-tools \
    qtdeclarative \
    qtdeclarative-plugins \
    qtdeclarative-qmlplugins \
    qtdeclarative-tools \
    qtquick1 \
    qtquick1-plugins \
    qtquick1-tools \
    qtwayland \
    qtwayland-examples \
    qtwayland-plugins \
    qtwayland-tools \
    qtgraphicaleffects-qmlplugins \
    "

# qtwebkit
# qtwebkit-qmlplugins
