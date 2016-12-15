FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

PACKAGECONFIG_append = " gstreamer"

SRC_URI_append = " \
    file://0001-metadata-image-support-with-the-data-URL-scheme-for-.patch \
    file://0001-GStreamer-support-date-time-type-in-metadata.patch \
    file://0001-GStreamer-support-cover-art.patch \
    file://0001-GStreamer-support-cover-art-image.patch \
    "
