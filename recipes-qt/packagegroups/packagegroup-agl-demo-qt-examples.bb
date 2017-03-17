SUMMARY = "The examples to test Qt5 on AGL Demo Platform"
DESCRIPTION = "A set of packages which contains Qt5 examples"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-demo-qt-examples \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    qt3d-examples \
    qtbase-examples \
    qtconnectivity-examples \
    qtdeclarative-examples \
    qtlocation-examples \
    qtmultimedia-examples \
    qtsensors-examples \
    qtsystems-examples \
    qttools-examples \
    qtwayland-examples \
    qtxmlpatterns-examples \
    "
