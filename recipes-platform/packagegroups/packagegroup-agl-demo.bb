SUMMARY = "The software for AGL IVI DEMO profile"
DESCRIPTION = "A set of packages belong to AGL Demo"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-demo \
    "

ALLOW_EMPTY_${PN} = "1"

SMARTDEVICELINK = "${@bb.utils.contains('DISTRO_FEATURES', 'agl-sdl', \
    'packagegroup-agl-smartdevicelink', '', d)}"

RDEPENDS_${PN} += "\
    packagegroup-agl-appfw \
    homescreen \
    udisks \
    ${SMARTDEVICELINK} \
    "

# fonts
TTF_FONTS = " \
    ttf-bitstream-vera \
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
    ttf-dejavu-serif \
    "

# Add webkit as workaround as webengine is broken right now
EXTRA_APPS_append = " qtwebkit qtwebkit-examples-examples"
#EXTRA_APPS_append = " qtsmarthome cinematicexperience qt5everywheredemo qt5-demo-extrafiles"
#IMAGE_INSTALL_append = " qtwebengine-examples"

# add support for websocket in Qt and QML
EXTRA_APPS_append = " qtwebsockets qtwebsockets-qmlplugins"
PREFERRED_PROVIDER_virtual/webruntime = "web-runtime"


RDEPENDS_${PN} += " \
    linux-firmware-ath9k \
    can-utils \
    iproute2 \
    python-curses \
    dhcp-client \
    ${TTF_FONTS} \
    ${EXTRA_APPS} \
    "
