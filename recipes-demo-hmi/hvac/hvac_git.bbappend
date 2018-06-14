FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = "\
    file://0001-Changing-LED-colour-gradient-according-to-temperatur.patch \
    file://0002-Change-led-device-number-for-kingfisher.patch \
    file://0003-ALS2018-enable-PWM-fan-demo-by-lin-transceiver.patch \
"
