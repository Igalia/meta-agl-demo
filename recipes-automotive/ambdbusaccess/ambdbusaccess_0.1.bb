SUMMARY = "ambdbusaccess"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad \
                    file://ambdbusaccess.cpp;startline=1;endline=5;md5=0ba6897a99e9332dae1891b4ccf78de3 \
                   "

SRC_URI = "file://* \
          "
S = "${WORKDIR}"

DEPENDS = "qtdeclarative qtbase"
RDEPENDS_${PN} = "automotive-message-broker"

RPROVIDES_${PN} = "ambdbusaccess"

inherit qmake5

do_install () {
        install -d ${D}${libdir}/qt5/qml/Automotive/
        install -m 0755 ambdbusaccess ${D}${libdir}/qt5/qml/Automotive/
}

FILES_${PN} += "${libdir}/qt5/qml/Automotive/ambdbusaccess"
FILES_${PN}-dbg += "${libdir}/qt5/qml/Automotive/.debug"

