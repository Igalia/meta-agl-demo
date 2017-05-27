#!/bin/sh

if test -z "$XDG_CONFIG_DIRS"; then
    export XDG_CONFIG_DIRS=/etc/xdg/
fi

if test -z "$XDG_RUNTIME_DIR"; then
    export XDG_RUNTIME_DIR=/run/user/$UID
    mkdir --parents $XDG_RUNTIME_DIR
    chmod 0700 $XDG_RUNTIME_DIR
fi

# Default homescreen
HOMESCREEN=/usr/share/openivi/example/cluster/index.html

# Read configuration
HOMESCREEN_CONFIG=$XDG_CONFIG_DIRS/openivi-html5/openivi-html5.ini
if [ -e "$HOMESCREEN_CONFIG" ] ; then
    HTML=`sed -n '/^homescreen=\(.*\)$/s//\1/p' <  $HOMESCREEN_CONFIG`
    if [ -n "$HTML" ] ; then
        HOMESCREEN=$HTML
    fi
fi

#the following value shall be modified for your display side
SCREEN_W=1080
SCREEN_H=1920

# Demo is configured to FullHD
QT_W=1080
QT_H=1920

QT_IVI_SURFACE_ID=4194304 QT_WAYLAND_SHELL_INTEGRATION=ivi-shell /usr/bin/openivi-html5 -f -u $HOMESCREEN &

# qmlscene create 2 surfaces
#   0x80000000 : for off screen buffer ?
#   0x80000001 : visible
#
SURFACE_ID_QML=0x80000001

#
# layer-add-surfaces wait till 2 surfaces are created.
#
layer-add-surfaces 1000 2

/usr/bin/LayerManagerControl set surface $SURFACE_ID_QML destination region 0 0 $SCREEN_W $SCREEN_H
/usr/bin/LayerManagerControl set surface $SURFACE_ID_QML source region 0 0 $QT_W $QT_H
/usr/bin/LayerManagerControl set layer 1000 render order $SURFACE_ID_QML
/usr/bin/LayerManagerControl set surfaces $SURFACE_ID_QML input focus keyboard
/usr/bin/LayerManagerControl set screen 0 render order 1000
