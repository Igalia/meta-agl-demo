#!/bin/sh

CONNMAN_CONF=/etc/connman/main.conf
CLUSTER_ADDRESS=192.168.20.93

if [ -z "$1" ]; then
    echo "Usage: $0 <network device>"
    exit 1
fi

# Need to blacklist given device with connman if it isn't already,
# otherwise connman will over-ride address configuration.
if ! grep '^NetworkInterfaceBlacklist=' ${CONNMAN_CONF} | grep -q $1; then
    sed -i "s/^\(NetworkInterfaceBlacklist=.*\)/\1,$1/" ${CONNMAN_CONF}
fi

/sbin/ifconfig $1 ${CLUSTER_ADDRESS}
