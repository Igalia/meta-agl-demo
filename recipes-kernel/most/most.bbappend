FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
	    file://0001-src-most-net-add-skb_put_data-function.patch \
	    file://0002-src-most-add-auto-conf-feature.patch \ 
	    file://0003-core-remove-kernel-log-for-MBO-status.patch \
	    file://0004-most-video-set-device_caps.patch \
	    file://0005-most-video-set-V4L2_CAP_DEVICE_CAPS-flag.patch \
	   "
