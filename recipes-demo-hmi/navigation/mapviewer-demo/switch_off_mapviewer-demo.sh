#!/bin/sh

/bin/systemctl disable weston-mapviewer-demo.service
/bin/systemctl enable weston.service
/bin/systemctl disable mapviewer.service
