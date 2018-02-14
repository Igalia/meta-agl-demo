FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI_append = "file://dbus-c++-threading.patch"
