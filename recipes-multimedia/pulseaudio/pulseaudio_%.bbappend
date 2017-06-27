FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0006-auto-load-module-router.patch"
SRC_URI += "file://0001-disable-module-role-cork-by-default.patch"
