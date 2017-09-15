FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_ulcb = " \
    file://0001-Add-virtual-output-support.patch \
    file://0002-Add-gst-recorder-for-h264-output-streaming.patch \
    file://0003-gst-recorder-Use-USERPTR-instead-of-DMABUF-for-VSP-o.patch \
    file://0004-gst-record-Specify-bytesused-and-length-of-VSP-input.patch \
"

