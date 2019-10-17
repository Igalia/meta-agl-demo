#!/bin/sh
LMC=/usr/bin/LayerManagerControl
IFS=$'\n'

SURFACEID=16777216

while :
do
    /bin/sleep 1
    surfaces=()
    for line in $($LMC get surfaces 2> /dev/null); do
        if [ "X-" = "X$(echo $line | awk '{print $1}')" ]; then
            surfaceid=$(echo $line | awk '{print $3}')
            if [ "$surfaceid" = "$SURFACEID" ]; then
                $LMC set surface $SURFACEID source region 0 0 384 368
                $LMC set surface $SURFACEID destination region 0 0 384 368
                $LMC set surface $SURFACEID visibility 1
                $LMC set layer 11001 render order $SURFACEID
                exit
            fi
        fi
    done
done
