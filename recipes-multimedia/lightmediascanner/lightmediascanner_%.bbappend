EXTRA_OECONF = "--enable-static"
PACKAGECONFIG[mp4] = "--enable-mp4,--disable-mp4,libmp4v2"

# add support MP3 because of the format of music files for AGL CES/ALS2017 Demo
PACKAGECONFIG_append = " id3 mp4"

# add required character sets for id3 tag scanning
RDEPENDS_${PN}_append = " glibc-gconv-utf-16 glibc-gconv-iso8859-1"
