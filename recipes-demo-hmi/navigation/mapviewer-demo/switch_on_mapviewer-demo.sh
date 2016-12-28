#!/bin/sh

/bin/systemctl disable weston.service
/bin/systemctl enable weston-mapviewer-demo.service
/bin/systemctl enable mapviewer.service

