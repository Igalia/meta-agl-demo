DESCRIPTION = "GENIVI Audio Manager Plugins"
HOMEPAGE = "http://projects.genivi.org/audio-manager/home"
LICENSE = "MPLv2"
SECTION = "multimedia"
DEPENDS = "audiomanager libxml2 dbus pulseaudio"

LIC_FILES_CHKSUM = "file://PluginControlInterfaceGeneric/LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = " \
    git://github.com/tisogai/AudioManagerPlugins.git;protocol=https;branch=unknown_element_support \
  "

SRCREV = "bf5c5e8198b85e84363770150d5a02f553f43296"

S = "${WORKDIR}/git"
inherit cmake
OECMAKE_CXX_FLAGS += "-std=c++11"
EXTRA_OECMAKE = " \
    -DWITH_COMMAND_INTERFACE_CAPI=OFF \
    -DWITH_COMMAND_INTERFACE_DBUS=ON \
    -DWITH_ROUTING_INTERFACE_ASYNC=OFF \
    -DWITH_ROUTING_INTERFACE_CAPI=OFF \
    -DWITH_ROUTING_INTERFACE_DBUS=ON \
    -DWITH_TEST_CONTROLLER=OFF \
    -DWITH_GENERIC_CONTROLLER=ON \
    -DCONTROLLER_CONFIG_DIR="/etc/audiomanager/control" \
"

# NOTE: The specific product should have a separate customtypes.xsd
# and should be appended to FILES_${PN} in product specific
# audiomanager-generic-controller_git.bbappend
FILES_${PN} = " \
    ${libdir}/audiomanager/control/libPluginControlInterfaceGeneric.so \
    ${sysconfdir}/audiomanager/control/audiomanagertypes.xsd \
    ${libdir}/audiomanager/command/libPluginCommandInterfaceDbus.so \
    ${libdir}/audiomanager/routing/libPluginRoutingInterfaceDbus.so \
    /usr/share/audiomanager/audiomanager/CommandInterface.xml \
    /usr/share/audiomanager/audiomanager/RoutingReceiver.xml \ 
"

# NOTE: The specific product should have a separate customtypes.h
# and should be appended to FILES_${PN}-dev in product specific
# audiomanager-generic-controller_git.bbappend
FILES_${PN}-dev = " \
    ${sysconfdir}/audiomanager/control/xsd2header.pl \
"

FILES_${PN}-dbg += " \
    ${libdir}/audiomanager/control/.debug/libPluginControlInterfaceGeneric.so \
    ${libdir}/audiomanager/command/.debug/libPluginCommandInterfaceDbus.so \
    ${libdir}/audiomanager/routing/.debug/libPluginRoutingInterfaceDbus.so \
"

# remove example configurations
do_install_append() {
    rm -rf ${D}${sysconfdir}/audiomanager/control/*.xml
    rm -rf ${D}${sysconfdir}/audiomanager/control/customtypes.xsd
}

RDEPENDS_${PN} = "virtual/audiomanager-plugins-config"
