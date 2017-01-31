#!/bin/sh

/bin/systemctl disable weston.service
/bin/systemctl enable weston-mapviewer-demo.service
/bin/systemctl enable mapviewer.service

/bin/ln -sf /usr/local/lib/libmmngrbuf.so.1 /usr/lib

sync
