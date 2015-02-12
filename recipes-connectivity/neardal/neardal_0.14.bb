require neardal.inc

SRC_URI = "https://github.com/connectivity/neardal/archive/${PV}.tar.gz \
	file://ncl.patch \
    file://0001-neardal-ncl-fix-segfault-on-help-page-being-displaye.patch \
    "
SRC_URI[md5sum] = "3dbda58253ca30ee6a7a7573eaa68f40"
SRC_URI[sha256sum] = "157d320bd831d91a82203d9697d2d2a2cebdb515d6e1c4ce04fe8ef27d1da615"
