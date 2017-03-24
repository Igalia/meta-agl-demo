IMAGE_FEATURES =+ "debug-tweaks"

DISTRO_FEATURES_append = " webruntime "

# Add webkit as workaround as webengine is broken right now
IMAGE_INSTALL_append = " qtwebkit qtwebkit-examples-examples"
#IMAGE_INSTALL_append = " qtsmarthome cinematicexperience qt5everywheredemo qt5-demo-extrafiles"
#IMAGE_INSTALL_append = " qtwebengine-examples"

# add support for websocket in Qt and QML
IMAGE_INSTALL_append = " qtwebsockets qtwebsockets-qmlplugins"

IMAGE_INSTALL_append = " screen "

PREFERRED_PROVIDER_virtual/webruntime = "web-runtime"
