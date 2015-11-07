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
> revision: df87cb27efeaea1455f20692f9f1397c6fcab254

URI: git://git.openembedded.org/meta-openembedded
> layer:    meta-oe
> branch:   dizzy
> revision: 9efaed99125b1c4324663d9a1b2d3319c74e7278

URI: https://gerrit.automotivelinux.org/gerrit/AGL/meta-agl
> branch:   master
> revision: 4d71b6fbe454ff51342ab1eb6791fad66ba98c3e
> (or later)

URI: https://github.com/meta-qt5/meta-qt5.git
> branch:   dizzy
> revision: adeca0db212d61a933d7952ad44ea1064cfca747

## The Renesas R-Car Gen2 (Porter) board depends in addition on: ##

URI: https://gerrit.automotivelinux.org/gerrit/AGL/meta-renesas
> branch:   agl-1.0-bsp-1.8.0
> revision: bf30de66badcac7ef82d3758aa44c116ee791a28
> (or later)

Packagegroups
-------------

AGL Demo Platform's package group design:

* packagegroup-agl-demo-platform

This is for making image 'agl-demo-platform' which is full image
for IVI profile of AGL distro.

As meta-agl's design of packagegroups, ``agl-demo-platform.bb`` contains
only ``packagegroup-agl-demo-platform`` and packages of DEMO apps.

``packagegroup-agl-demo-platform`` has 4 packagegroups in it,
``packagegroup-agl-core``, ``packagegroup-agl-ivi``,
``packagegroup-agl-ivi-common-core``, and ``packagegroup-agl-appfw``.

* packagegroup-agl-appfw*

These packagegroups contains packages for application framework of AGL Distro.
Subsystem should maintain ``packagegroup-agl-appfw-[subsystem].bb`` which
should hold sufficient packages for application framework of AGL Distro.

Subsystems also can maintain their own packagegroups under appropriate
``recipes-*/``.

For example, Qt5 has 2 packagegroups in ``meta-agl-demo``,
``packagegroup-agl-appfw-native-qt5`` and ``packagegroup-agl-demo-qt-examples``
which are under ``recipes-qt/``.

The ``packagegroup-agl-appfw-native-qt5`` is included by
``packagegroup-agl-appfw-native`` because Qt5 belongs to native application
framework of AGL Distro.

The ``packagegroup-agl-demo-qt-examples`` is added to local.conf if needed
because they are not mandatory for AGL application framework and AGL Demo
Platform.

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
                meta-qt5/
                poky/

* For R-Car M2

      ${TOPDIR}/
                meta-agl/
                meta-agl-demo/
                meta-openembedded/
                meta-qt5/
                meta-renesas/
                poky/

Downloading the Source
--------------------
You can use repo tool to get all layers which are needed to build AGL Distribution.

1. Installing Repo. Make sure you have a bin/ in your $HOME and it's included in your $PATH.
        $ mkdir ~/bin
        $ export PATH=~/bin:$PATH

   Download the repo tool.
        $ curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
        $ chmod a+x ~/bin/repo

2. Preparing download. Create an empty directory to hold all recipes and build environment. You can make it as any name you like.
        $ mkdir WORKING-DIRECTORY
        $ cd WORKING-DIRECTORY

3. Getting all layers.
        $ repo init -u https://gerrit.automotivelinux.org/gerrit/AGL/AGL-repo
        $ repo sync

Build a QEMU image
------------------

You can build a QEMU image using the following steps:

1. Set up environment: You can specify any name for directory which storing all stuffs to build.
        $ source meta-agl/scripts/envsetup.sh qemux86-64 [BUILD_DIR]

2. Build the full image of AGL Demo Platform and applications
        $ bitbake agl-demo-platform

  2a. Specifically If you are building the CES2016 demos you will want to add the following to your "conf/local.conf" file to install the demo code in the image:
IMAGE_INSTALL_append = " CES2016-demo"

  2b. If you want to run QEMU directly as VM in Virtual Box or your other favorite VM software then add this line to your "conf/local.conf" file. 
IMAGE_FSTYPES += "vmdk"

  2c. The Weston IVI-Shell always gets built ; it will not be started, however, unless you specify the following in your "conf/local.conf" file :

IMAGE_INSTALL_append = " \
    weston-ivi-shell-config \
    "
      or you manually overwrite the "/etc/xdg/weston/weston.ini" file with a correct one.

3. Run the emulator. The path for the emulator (runqemu) was added during the envsetup.
        $ cd tmp/deploy/images/qemex86-64
        $ runqemu bzImage-qemux86-64.bin agl-demo-platform-qemux86-64.ext3

   For large screen:
        $ runqemu bzImage-qemux86-64.bin agl-demo-platform-qemux86-64.ext3 \
        bootparams="uvesafb.mode_option=1280x720-32"

   To extend the amount of memory, add to runqemu:
        qemuparams="-m 512"

   or use the virtual disk in Virtual Box from this location:
	tmp/deploy/images/qemux86-64/agl-demo-platform-qemux86-64.vmdk 

4. Some weston samples are available from weston terminal.




Build a R-Car M2 (porter) image
-------------------------------

#### Obtain and Install Renesas Graphics/Multimedia Drivers

1. Download packages from Renesas

  The graphics and multimedia acceleration packages for the R-Car M2 Porter board
  can be download directory from [here](http://www.renesas.com/secret/r_car_download/rcar_demoboard.jsp).

  There are 2 ZIP files can be downloaded.
    * Multimedia and Graphics library which require registeration and click through license
    > r-car_series_evaluation_software_package_for_linux-*.zip
    * Related Linux drivers
    > r-car_series_evaluation_software_package_of_linux_drivers-*.zip

  These 2 files from Renesas should be store in your download directory in $HOME. (e.g. $HOME/Downloads) If not, envsetup.sh in below will stop and show some instruction, then please follow it.

#### Build from the Source code

You can build a R-Car2 M2 (porter) image using the following steps:

1. Set up environment: You can specify any name for directory which storing all stuffs to build.
        $ source meta-agl/scripts/envsetup.sh porter [BUILD_DIR]

2. (Optional) If you want to install various Qt5 examples, add below
   configuration to your local.conf.
        IMAGE_INSTALL_append = " \
            packagegroup-agl-demo-qt-examples \
        "
        PACKAGECONFIG_append_pn-qtbase = " examples"

   IMPORTANT NOTE:
        To run examples with wayland-egl plugin,
        use ``LD_PRELOAD=/usr/lib/libEGL.so <command>``.
        If not, programs should not launch by error,
        'EGL not available'.

3. (Optional) If you want to use multimedia accelerations, uncomment
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
           $ sudo cp uImage uImage-r8a7791-porter.dtb /media/$SDCARD_LABEL/boot

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

