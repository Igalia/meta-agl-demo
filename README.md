meta-agl-demo, the Yocto layer for DEMO platform of Automotive Grade Linux
==========================================================================

The yocto layer 'meta-agl-demo' provides a demo platform and applications
of AGL Distribution.

AGL is creating an automotive specific Linux distribution that unifies
the software that has been written in a number of places already,
such as GENIVI and Tizen IVI.

The AGL community appreciates feedback, ideas, suggestion, bugs and
documentation just as much as code. Please join the irc conversation
at the #automotive channel on irc.freenode.net and our mailing list.

For infomation for subscribing to the mailing list
    [automotive-discussions](http://lists.linuxfoundation.org/mailman/listinfo/automotive-discussions)
For information about AGL Distribution, see the
    [AGL Distribution](https://wiki.automotivelinux.org/agl-distro)
For information abount Getting started with AGL
    [here](https://wiki.automotivelinux.org/start/getting-started)
For information about contributing to the AGL Distro
    [here](https://wiki.automotivelinux.org/agl-distro/contributing)

Layer Dependencies
------------------

URI: git://git.yoctoproject.org/poky
> branch:   dizzy
> revision: 5f0d25152bac2d3798663a4ebfdd2df24060f153

URI: git://git.openembedded.org/meta-openembedded
> layer:    meta-oe
> branch:   dizzy
> revision: 853dcfa0d618dc26bd27b3a1b49494b98d6eee97

URI: https://gerrit.automotivelinux.org/gerrit/AGL/meta-agl
> branch:   master
> revision: 4d71b6fbe454ff51342ab1eb6791fad66ba98c3e
> (or later)

## The Renesas R-Car Gen2 (Porter) board depends in addition on: ##

URI: https://gerrit.automotivelinux.org/gerrit/AGL/meta-renesas
> branch:   agl-1.0-bsp-1.8.0
> revision: bf30de66badcac7ef82d3758aa44c116ee791a28
> (or later)

Packagegroups
-------------

AGL Demo Platform's package group design:

Top-level packagegroup,
> packagegroup-agl-appfw (all packages for demo platform)

This package group can contain sub-package groups like these.
> packagegroup-agl-appfw-graphics (for graphics subsystem)
> packagegroup-agl-appfw-agl (for AGL App Fw subystem)
> ...

The "packagegroups-agl-appfw-*" will contain packages for application and
frameworks.
> directory: meta-agl-demo/recipes-demo-platform/packagegroups
> recipes  : packagegroup-agl-appfw-[subsystem].bb

Supported Machine
-----------------

* QEMU (x86-64) - emulated machine: qemux86-64
* Renesas R-Car Gen2 (R-Car M2) - machine: porter

Supported Target of bitbake
------------------------

* `agl-demo-platform` The full image of AGL Demo Platform and applications

Supposed Directory Trees of Layers to build
-------------------------------------------

* For QEMU

      ${TOPDIR}/
                meta-agl/
                meta-agl-demo/
                meta-openembedded/
                poky/

* For R-Car M2

      ${TOPDIR}/
                meta-agl/
                meta-agl-demo/
                meta-openembedded/
                meta-renesas/
                poky/

Build a QEMU image
------------------

You can build a QEMU image using the following steps:

1. Export TEMPLATECONF to pick up correct configuration for the build
        $ export TEMPLATECONF=/full/path/to/meta-agl-demo/conf

2. Run the following command:
        $ source poky/oe-init-build-env

3. Build the full image of AGL Demo Platform and applications
        $ bitbake agl-demo-platform

4. Run the emulator
        $ cd $BUILD_DIR/tmp/deploy/images/qemex86-64
        $ PATH_TO_POKY/poky/scripts/runqemu qemux86-64 bzImage-qemux86-64.bin \
        agl-demo-platform-qemux86-64.ext3

   For large screen:
        $ PATH_TO_POKY/poky/scripts/runqemu qemux86-64 bzImage-qemux86-64.bin \
        agl-demo-platform-qemux86-64.ext3 \
        bootparams="uvesafb.mode_option=1280x720-32"

5. Some weston samples are available from weston terminal.

Build a R-Car M2 (porter) image
-------------------------------

### Software setup

NOTE: These instructions are based on GENIVI wiki, [here](http://wiki.projects.genivi.org/index.php/Hardware_Setup_and_Software_Installation/koelsch%26porter). If these didn't work correctly especially around Renesas Binary Packages, please check there and updated instructions.

#### Getting Source Code and Build image

1. Create a directory for working, then go to there.
        $ mkdir -p $HOME/ANYWHERE_YOU_WANT_TO_WORK_THERE
        $ cd $HOME/ANYWHERE_YOU_WANT_TO_WORK_THERE
        $ export AGL_TOP=`pwd`

2. Get the meta data and checkout
        $ git clone git://git.yoctoproject.org/poky
        $ cd poky
        $ git checkout 5f0d25152bac2d3798663a4ebfdd2df24060f153
        $ cd -
        $ git clone git://git.openembedded.org/meta-openembedded
        $ cd meta-openembedded
        $ git checkout 853dcfa0d618dc26bd27b3a1b49494b98d6eee97
        $ cd -
        $ git clone https://gerrit.automotivelinux.org/gerrit/AGL/meta-agl
        $ cd meta-agl
        $ git checkout 4d71b6fbe454ff51342ab1eb6791fad66ba98c3e
        $ cd -
        $ git clone https://gerrit.automotivelinux.org/gerrit/AGL/meta-renesas
        $ cd meta-renesas
        $ git checkout bf30de66badcac7ef82d3758aa44c116ee791a28
        $ cd -
        $ git clone https://gerrit.automotivelinux.org/gerrit/AGL/meta-agl

#### Obtain and Install Renesas Graphics/Multimedia Drivers

1. Download packages from Renesas

  The graphics and multimedia acceleration packages for the R-Car M2 Porter board
  can be download directory from [here](http://www.renesas.com/secret/r_car_download/rcar_demoboard.jsp).

  There are 2 ZIP files can be downloaded.
    * Multimedia and Graphics library which require registeration and click through license
    > r-car_series_evaluation_software_package_for_linux-*.zip
    * Related Linux drivers
    > r-car_series_evaluation_software_package_of_linux_drivers-*.zip

2. Unzip the two downloads into a temporary directory.
        $ cd $AGL_TOP
        $ mkdir binary-tmp
        $ cd binary-tmp
        $ unzip PATH_TO_DOWNLOAD/r-car_series_evaluation_software_package_for_linux-*.zip
        $ unzip PATH_TO_DOWNLOAD/r-car_series_evaluation_software_package_of_linux_drivers-*.zip

   After this step there should be two files in binary-tmp:
   * Multimedia and Graphics library
   > R-Car_Series_Evaluation_Software_Package_for_Linux-*.tar.gz
   * Related Linux drivers
   > R-Car_Series_Evaluation_Software_Package_of_Linux_Drivers-*.tar.gz

3. Copy the graphics acceleration drivers by shell script.
        $ cd $AGL_TOP/meta-renesas/meta-rcar-gen2
        $ ./copy_gfx_software_porter.sh ../../binary-tmp

4. Copy the multimedia acceleration drivers by shell script.
        $ cd $AGL_TOP/meta-renesas/meta-rcar-gen2
        $ ./copy_mm_software_lcb.sh ../../binary-tmp

#### Build from the Source code

You can build a R-Car2 M2 (porter) image using the following steps:

1. Export TEMPLATECONF to pick up correct configuration for the build
        $ export TEMPLATECONF=$AGL_TOP/meta-renesas/meta-rcar-gen2/conf

2. (Optional) If you want to use multimedia accelerations, uncomment
   manually 4 `IMAGE_INSTALL_append_porter` in conf/local.conf.
        #IMAGE_INSTALL_append_porter = " \
        #    gstreamer1.0-plugins-bad-waylandsink \
        #    "

        #IMAGE_INSTALL_append_porter = " \
        #    gstreamer1.0-plugins-base-videorate \
        ...
        #"

        #IMAGE_INSTALL_append_porter = " \
        #    libegl libegl-dev libgbm-dev \
        ...
        #    "

        #IMAGE_INSTALL_append_porter = " \
        #    packagegroup-rcar-gen2-multimedia \
        ...
        #    "

   Also it is needed to uncomment this,
        #MACHINE_FEATURES_append = " multimedia"

   This `multimedia` enables meta-renesas's multimedia configuration.
   The version of GStreamer1.0 which AGL distro use, will be changed
   to 1.2.3 (meta-renesas prefers) from 1.4.1(meta-agl default) by this switch.

3. Run the following command:
        $ cd $AGL_TOP
        $ source poky/oe-init-build-env

   (Optional) If you want to use multimedia accelerations, confirm your
   conf/bblayer.conf has a entry of `meta-openembedded/meta-multimedia`
   in BBLAYERS because packagegroup-rcar-gen2-multimedia needs some extra
   packages there.

4. Build the full image of AGL Demo Platform and applications
        $ bitbake agl-demo-platform

### Deployment (SDCARD)

NOTE: These instructions are based on GENIVI wiki, [here](http://wiki.projects.genivi.org/index.php/Hardware_Setup_and_Software_Installation/koelsch%26porter#Deployment_.28SDCARD.29).

#### Instructions on the host

1. Format SD-Card and then, create single EXT3 partition on it.

2. Mount the SD-Card, for example `/media/$SDCARD_LABEL`.

3. Copy AGL root file system onto the SD-Card
   1. Go to build directory
           $ cd $AGL_TOP/build/tmp/deploy/images/porter
   2. Extract the root file system into the SD-Card
           $ sudo tar --extract --numeric-owner --preserve-permissions --preserve-order \
           --totals --directory=/media/$SDCARD_LABEL --file=agl-demo-platform-porter.tar.bz2
   3. Copy kernel and DTB into the `/boot` of the SD-Card
           $ sudo cp uImage uImage-r8a7791-porter.dtb /media/$SDCARD_LABEL

4. After the copy finished, unmount SD-Card and insert it into the SD-Card slot of the porter board.

#### Instructions on the host

NOTE: There is details about porter board [here](http://elinux.org/R-Car/Boards/Porter).

NOTE: To boot weston on porter board, we need keyboard and mouse. (USB2.0 can be use for this)

##### Change U-Boot parameters to boot from SD card

1. Power up the board and, using your preferred terminal emulator, stop the board's autoboot by hitting any key.

  > Debug serial settings are 38400 8N1. Any standard terminal emulator program can be used.

2. Set the follow environment variables and save them
        => setenv bootargs_console console=ttySC6,${baudrate}
        => setenv bootargs_video vmalloc=384M video=HDMI-A-1:1024x768-32@60
        => setenv bootcmd_sd 'ext4load mmc 0:1 0x40007fc0 boot/uImage;ext4load mmc 0:1 0x40f00000 boot/uImage-r8a7791-porter.dtb'
        => setenv bootcmd 'setenv bootargs ${bootargs_console} ${bootargs_video} root=/dev/mmcblk0p1 rw rootfstype=ext3;run bootcmd_sd;bootm 0x40007fc0 - 0x40f00000'
        => saveenv

##### Boot from SD card

1. After board reset, U-Boot is started and after a countdown, ...
   Linux boot message should be displayed. Please wait a moment.
2. Then weston is booted automatically, and weston-terminal appears.

3. Have fun! :)

4. (Optional) This is how to test and play multimedia contents with acceleration.

    1. Boot porter without mouse and keyboard, it avoid to boot weston automatically.
       For now, when running weston, V4L2 deosn't work correctly, so we have to
       stop weston first (GST plugin `waylandsink` also doesn't work correctly for now).

    2. Execute these instructions:
            $ export LD_LIBRARY_PATH="/lib:/usr/lib:/usr/local/lib:"

            # Set the mixer
            $ amixer set "LINEOUT Mixer DACL" on
            $ amixer set "DVC Out" 10

            $ modprobe -a mmngr mmngrbuf s3ctl uvcs_cmn vspm fdpm

            $ media-ctl -d /dev/media0 -r
            $ media-ctl -d /dev/media0 -l '"vsp1.2 rpf.0":1 -> "vsp1.2 uds.0":0 [1]'
            $ media-ctl -d /dev/media0 -l '"vsp1.2 uds.0":1 -> "vsp1.2 wpf.0":0 [1]'
            $ media-ctl -d /dev/media0 -l '"vsp1.2 wpf.0":1 -> "vsp1.2 lif":0 [1]'
            $ media-ctl -d /dev/media0 -V '"vsp1.2 rpf.0":0 [fmt:AYUV32/1920x1080]'
            $ media-ctl -d /dev/media0 -V '"vsp1.2 rpf.0":1 [fmt:AYUV32/1920x1080]'
            $ media-ctl -d /dev/media0 -V '"vsp1.2 uds.0":0 [fmt:AYUV32/1920x1080]'
            $ media-ctl -d /dev/media0 -V '"vsp1.2 uds.0":1 [fmt:AYUV32/640x480]'
            $ media-ctl -d /dev/media0 -V '"vsp1.2 wpf.0":0 [fmt:AYUV32/640x480]'
            $ media-ctl -d /dev/media0 -V '"vsp1.2 wpf.0":1 [fmt:ARGB32/640x480]'
            $ media-ctl -d /dev/media0 -V '"vsp1.2 lif":0 [fmt:ARGB32/640x480]'

            # in caes R-Car M2 (HDMI - DU1 - vspd0)
            $ modetest -M rcar-du -s 10@8:1280x720@AR24 -d -P '8@19:640x480+100+200@XR24' &

    After these command, Test pattern will show on display connected to
    porter's HDMI port.

    Then, you can play H264(MP4) movie like these,
            $ gst-launch-1.0 filesrc location=./sample.mp4  ! qtdemux name=d d. ! \
            queue ! omxh264dec no-copy=true ! v4l2sink device=/dev/video1 \
            io-mode=userptr d. ! queue ! faad ! alsasink device=hw:0,0

