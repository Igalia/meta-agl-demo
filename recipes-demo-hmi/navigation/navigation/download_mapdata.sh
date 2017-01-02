#!/bin/sh
#
# AGL Navigation mapdata download scripts
#

#--------------------------------------------------------------
help()
{
bn=`basename $0`
cat << EOF
usage
host: sudo $bn 'target_rootfs_path/'
target : $bn /
EOF

}
#-check para-------------------------------------------------------
shift `expr $OPTIND - 1`

if [ $# != 1  ]; then
        help
        exit
fi

rootfs=$1

#----------------------------------------------------------------

# check the if root? ------------------------------
userid=`id -u`
if [ $userid -ne "0" ]; then
        echo "you're not root? run with sudo"
        exit
fi

if [ ! -e $1 ]; then
	echo "rootfs:$1 not found"
	exit
fi

if [ ! -f $HOME/navi_data_UK.tar.gz ]; then
	echo "no map data"
	echo "start downloading..."
	wget --directory-prefix=$HOME http://agl.wismobi.com/data/UnitedKingdom_TR9/navi_data_UK.tar.gz
else
	echo "use downloaded map data"
fi

mapdatadir=$rootfs/var/mapdata

if [ ! -d $mapdatadir ]; then
	echo "map data directory does not exist"
	echo "create a directory" $mapdatadir
	mkdir -p $mapdatadir
else
	echo "map data directory exists"
fi

tar xvzf $HOME/navi_data_UK.tar.gz -C $mapdatadir
sync
echo "done.."
