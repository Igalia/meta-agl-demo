SUMMARY     = "AGL Home Screen Library"
DESCRIPTION = "libhomescreen"
HOMEPAGE    = "http://docs.automotivelinux.org"
LICENSE     = "Apache-2.0"
SECTION     = "libs"

BBCLASSEXTEND = " nativesdk"

require ${@bb.utils.contains('DISTRO_FEATURES', 'agl-hmi-framework', 'libhomescreen-2017.inc', 'libhomescreen-old.inc', d)}
