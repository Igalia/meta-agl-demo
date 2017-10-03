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
    qtbase-plugins \
    qtbase-staticdev \
    qtbase-tools \
    qtdeclarative \
    qtdeclarative-qmlplugins \
    qtdeclarative-tools \
    qtcharts \
    qtwayland \
    qtwayland-plugins \
    qtwayland-tools \
    qtgraphicaleffects-qmlplugins \
    qtvirtualkeyboard \
    "
RDEPENDS_${PN}_checkforkrogoth += "\
    qtwayland-examples \
    "
