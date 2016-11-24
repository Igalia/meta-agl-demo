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
    if [ -e "$HTML" ] ; then
        HOMESCREEN=$HTML
    fi
fi

/usr/bin/openivi-html5 -f -u $HOMESCREEN
