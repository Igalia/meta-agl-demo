**README.md for the 'meta-agl-demo' layer.**

**See README-AGL.md for general information about Automotive Grade Linux.**


meta-agl-demo, the reference UI layer for the DEMO platform of Automotive Grade Linux
=================================================================================

The layer 'meta-agl-demo' provides a reference/demo platform and applications
for the AGL Distribution.

AGL is creating an automotive specific Linux distribution (AGL UCB) that unifies
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


Quick start guide
-----------------
See README-AGL.md


Layer Dependencies
------------------

* Base dependencies [agl-demo]:

URI: git://git.yoctoproject.org/poky
> branch         : jethro
> tested revision: 40376446904ae3529be41737fed9a0b650ed167d

URI: https://gerrit.automotivelinux.org/gerrit/AGL/meta-agl
> branch:   master

URI: git://git.openembedded.org/meta-openembedded
> layer          : meta-openembedded
> branch         : jethro
> tested revision: 8ab04afbffb4bc5184cfe0655049de6f44269990

Specifically out of meta-openembedded these sub-layers are used:

 - meta-openembedded/meta-oe
 - meta-openembedded/meta-multimedia
 -  meta-openembedded/meta-efl
 -  meta-openembedded/meta-networking
 -  meta-openembedded/meta-python
 -  meta-openembedded/meta-ruby

URI: https://github.com/meta-qt5/meta-qt5.git
> branch:   jethro (b/c of qt-5.5.x)
> tested revision: ea37a0bc987aa9484937ad68f762b4657c198617

* Hardware dependencies:

The Renesas R-Car Gen2 (Porter) board depends in addition on:

URI: https://gerrit.automotivelinux.org/gerrit/AGL/meta-renesas
> branch:   agl-1.0-bsp-1.8.0
> tested revision: 82611ccadef36ab0b8a6fd6fb1cf055e115f1ef5
> (or later)

* Extra feature dependencies:

 * The feature `agl-appfw-smack` has these dependencies
		* `meta-intel-iot-security`
		> URI: https://github.com/01org/meta-intel-iot-security
		> branch            : jethro
		> tested revision: c5906a1553513ef192a58231700357c5f14f4ae4

		* `meta-agl-extra/meta-app-framework`
		> URI: https://gerrit.automotivelinux.org/gerrit/gitweb?p=AGL/meta-agl-extra.git
		> branch            : master

 * The feature `agl-sota` has these dependencies:
		* `meta-agl-extra/meta-sota`
		> URI: https://gerrit.automotivelinux.org/gerrit/gitweb?p=AGL/meta-agl-extra.git
		> branch            : master

		* `meta-openembedded`
		> layer          : meta-openembedded
		> branch         : jethro
		> tested revision: 8ab04afbffb4bc5184cfe0655049de6f44269990

			* specifically:
				* `meta-openembedded/meta-filesystems`
				* `meta-openembedded/meta-ruby`


		* `meta-rust`
		> URI: https://github.com/konsulko/meta-rust
		> branch            : jethro
		> tested revision: 395cde581938d862abb6a9219c0118c81cf01da2

	* The feature `agl-netboot` has these dependenies:
		* `meta-agl/meta-netboot`

			> URI: https://gerrit.automotivelinux.org/gerrit/gitweb?p=AGL/meta-agl.git
			> branch            : master


Packagegroups
-------------

AGL Demo Platform's package group design:

* packagegroup-agl-demo-platform

This is for generating the image 'agl-demo-platform' which is a full image
for the IVI profile of the AGL distro.

As meta-agl's design of packagegroups, ``agl-demo-platform.bb`` contains
only ``packagegroup-agl-demo-platform`` and the packages of the DEMO applications.

``agl-demo-platform`` has 4 packagegroups in it,
``packagegroup-agl-image-minimal``, ``packagegroup-agl-image-ivi``,
``packagegroup-ivi-common-core``, and ``packagegroup-agl-demo-platform``.

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

Supported Machines
------------------

See `README-AGL.md` in meta-agl layer.

Supported Target of bitbake
------------------------

* `agl-demo-platform` is the full image of the AGL Demo Platform with all applications

