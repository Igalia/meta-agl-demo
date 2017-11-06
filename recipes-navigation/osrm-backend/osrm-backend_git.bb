SUMMARY     = "Open Source Routing Machine"
DESCRIPTION = "Open Source Routing Machine C++ backend"
HOMEPAGE    = "http://map.project-osrm.org"
SECTION     = "apps"

LICENSE     = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=d7862bc7b1d9f5fbeee5657b31075df1"

SRC_URI = "git://github.com/Project-OSRM/osrm-backend.git;protocol=http \
           file://0001-cmake-fix-search-paths-for-OE-building.patch \
           "

SRCREV  = "5b58445535633eb1d6840ef4e8786ff521622867"

FILES_${PN} += " \
               ${datadir}/osrm/profiles/*.lua \
               ${datadir}/osrm/profiles/*/*.lua \
               "

S = "${WORKDIR}/git"

DEPENDS = "boost bzip2 tbb libzip lua osmium protozero expat"

inherit cmake pkgconfig
