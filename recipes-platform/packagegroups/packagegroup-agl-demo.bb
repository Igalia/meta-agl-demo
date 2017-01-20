SUMMARY = "The software for AGL IVI DEMO profile"
DESCRIPTION = "A set of packages belong to AGL Demo"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-demo \
    "

ALLOW_EMPTY_${PN} = "1"


RDEPENDS_${PN} += "\
    packagegroup-agl-appfw \
    homescreen \
    "

# fonts
TTF_FONTS = " \
    ttf-bitstream-vera \
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
    ttf-dejavu-serif \
    "

RDEPENDS_${PN} += " \
    linux-firmware-ath9k \
    can-utils \
    iproute2 \
    python-curses \
    dhcp-client \
    ${TTF_FONTS} \
    "
