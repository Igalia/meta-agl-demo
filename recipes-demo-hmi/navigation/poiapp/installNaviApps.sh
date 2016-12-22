#!/bin/sh
/usr/bin/afm-util install poi.wgt

#it's Workaround
cyad -s -k MANIFESTS -t allow -c User::App::navigation -u '*' -p 'http://tizen.org/privilege/internal/dbus'
cyad -s -k MANIFESTS -t allow -c User::App::poi -u '*' -p 'http://tizen.org/privilege/internal/dbus'
