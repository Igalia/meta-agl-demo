SUMMARY = "climatecontrolplugin"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad \
                    file://climatecontrol.cpp;startline=1;endline=5;md5=0ba6897a99e9332dae1891b4ccf78de3 \
                    file://climatecontrol.h;startline=1;endline=5;md5=0ba6897a99e9332dae1891b4ccf78de3 \
                    file://climatecontrol_plugin.cpp;startline=1;endline=5;md5=0ba6897a99e9332dae1891b4ccf78de3 \
                    file://climatecontrol_plugin.h;startline=1;endline=5;md5=0ba6897a99e9332dae1891b4ccf78de3 \
                   "

SRC_URI = "file://* \
          "
S = "${WORKDIR}"

DEPENDS = "qtdeclarative qtbase"
RDEPENDS_${PN} = "automotive-message-broker ambdbusaccess"

inherit qmake5

do_install () {
        install -d ${D}${libdir}/qt5/qml/Automotive/ClimateControl/
        install -m 0755 libClimateControl.so ${D}${libdir}/qt5/qml/Automotive/ClimateControl/
        install -m 0644 qmldir ${D}${libdir}/qt5/qml/Automotive/ClimateControl/
}

FILES_${PN} += "${libdir}/qt5/qml/Automotive/ClimateControl/libClimateControl.so \
                ${libdir}/qt5/qml/Automotive/ClimateControl/qmldir \
               "
FILES_${PN}-dbg += "${libdir}/qt5/qml/Automotive/ClimateControl/.debug"

