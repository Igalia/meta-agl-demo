SUMMARY = "Neard Abstraction Library (NeardAL)"

DESCRIPTION = "NeardAL is a library that provides a simple C API \
to exchange data with NFC daemon (Neard)."

HOMEPAGE = "https://github.com/connectivity/neardal"
SECTION = "libs"
LICENSE = "LGPLv2"

DEPENDS = "glib-2.0 readline glib-2.0-native"

LIC_FILES_CHKSUM = "file://COPYING;md5=6a6e689d19255cf0557f3fe7d7068212 \
	file://lib/neardal.h;endline=20;md5=a53e93aea9357387078ed217565c0a99"

inherit autotools pkgconfig

PACKAGES =+ "${PN}-tools ${PN}-tools-dbg"

FILES_${PN}-tools = "${libexecdir}/ncl"
FILES_${PN}-tools-dbg += "${libexecdir}/.debug"

RDEPENDS_${PN}-tools = "bash"
