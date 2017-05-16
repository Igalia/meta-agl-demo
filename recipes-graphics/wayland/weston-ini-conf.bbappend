WESTONCORE[shell] ??= "ivi-shell.so"

WESTONIVISHELL[ivi-module] ??= "ivi-controller.so"
WESTONIVISHELL[ivi-input-module] ??= "ivi-input-controller.so"

WESTONSECTION[WESTONIVISHELL] = "ivi-shell"

# IVI cluster and virtual display sample
python () {
    if bb.utils.contains('DISTRO_FEATURES', 'virtual-display', True, False, d):
        d.setVarFlag("WESTONCORE", "virtual", "1")
}

WESTONOUTPUT2[agl_screen] ??= "SCREEN_VIRTUAL"

WESTONSECTION[WESTONOUTPUT2] = "output"

SCREEN_VIRTUAL[name]?="virtual1"
SCREEN_VIRTUAL[mode]?="384x368@60"

# If gst-record is set, recorder flag is true
python () {
    if bb.utils.contains('DISTRO_FEATURES', 'gst-record', True, False, d):
        d.setVarFlag("SCREEN_VIRTUAL", "recorder", "true")
}

SCREEN_VIRTUAL[ip]?="192.168.20.99"
SCREEN_VIRTUAL[port]?="5005"
SCREEN_VIRTUAL[bitrate]?="300000"
SCREEN_VIRTUAL[crop]?="384x368@0x0"

