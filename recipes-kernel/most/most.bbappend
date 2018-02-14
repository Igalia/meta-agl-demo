FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
	    file://0002-src-most-add-auto-conf-feature.patch \ 
	    file://0003-core-remove-kernel-log-for-MBO-status.patch \
	    file://0004-most-video-set-device_caps.patch \
	    file://0005-most-video-set-V4L2_CAP_DEVICE_CAPS-flag.patch \
	    file://0006-dim2-fix-startup-sequence.patch \
	    file://0007-dim2-use-device-tree.patch \
	    file://0008-dim2-read-clock-speed-from-the-device-tree.patch \
	    file://0009-dim2-use-device-for-coherent-memory-allocation.patch \
	   "
