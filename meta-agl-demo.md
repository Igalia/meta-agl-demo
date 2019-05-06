## Introduction

The `meta-agl-demo` layer is the reference user interface layer for the DEMO
platform of Automotive Grade Linux (AGL).
The layer provides a reference platform and applications.
The BitBake target name for the DEMO platform is `agl-demo-platform`, which is
the full DEMO platform image.

## Layer Dependencies

This section describes dependencies for the `meta-agl-demo` layer.
Dependencies are grouped into base, hardware, and feature dependencies.

### Base Dependencies

The `meta-agl-demo` layer has the following base dependencies:

* Yocto Project Release:

  - URI: git://git.yoctoproject.org/poky
  - Branch: "thud"
  - Tested Revision: See the [`default.xml`](https://github.com/leon-anavi/AGL-repo/blob/master/default.xml)
    manifest file for the `AGL-repo` repository for revision
    information.

* AGL `meta-agl` Layer:

  - URI: https://gerrit.automotivelinux.org/gerrit/AGL/meta-agl
  - Branch: "flounder"

* OpenEmbedded `meta-openembedded` Layer:

  - Branch: "thud"
  - Tested Revision: See the [`default.xml`](https://github.com/leon-anavi/AGL-repo/blob/master/default.xml)
    manifest file for the `AGL-repo` repository for revision
    information.

    Specifically, out of `meta-openembedded`, these sub-layers are used:

    - `meta-oe`
    - `meta-multimedia`
    - `meta-efl`
    - `meta-networking`
    - `meta-python`
    - `meta-ruby`

* Yocto Project `meta-qt5` Layer from the
  [OpenEmbedded Layer Index](https://layers.openembedded.org/layerindex/branch/master/layers/):

  - URI: https://github.com/meta-qt5/meta-qt5.git
  - Branch:   "thud"
  - Tested Revision: See the [`default.xml`](https://github.com/leon-anavi/AGL-repo/blob/master/default.xml)
    manifest file for the `AGL-repo` repository for revision
    information.

### Hardware Dependencies

Aside from the previously listed base dependencies, if you are using a
[supported Renesas board](../../../../getting_started/en/dev/reference/machines/renesas.html)
supported Renesas board, these dependencies exist:

* AGL's `meta-renesas` Layer:

  - URI: https://gerrit.automotivelinux.org/gerrit/AGL/meta-renesas
  - Branch: "agl-1.0-bsp-1.8.0"
  - Tested Revision: 82611ccadef36ab0b8a6fd6fb1cf055e115f1ef5 (or later)

### Feature Dependencies

The `meta-agl-demo` layer has the following AGL
[feature](../getting_started/reference/getting-started/image-workflow-initialize-build-environment.html#agl-features)
dependencies:

* Yocto Project `meta-security` Layer:

  - URI: https://git.yoctoproject.org/cgit/cgit.cgi/meta-security
  - Branch: "master"
  - Tested Revision: See the [`default.xml`](https://github.com/leon-anavi/AGL-repo/blob/master/default.xml)
    manifest file for the `AGL-repo` repository for revision
    information.

* AGL's `meta-app-framework` Layer Within the `meta-agl-extra` Layer:

  - URI: https://gerrit.automotivelinux.org/gerrit/gitweb?p=AGL/meta-agl-extra.git
  - Branch: "master"

**The `agl-sota` Feature:**

* AGL's `meta-sota` Layer Within the `meta-agl-extra` Layer:

  - URI: https://gerrit.automotivelinux.org/gerrit/gitweb?p=AGL/meta-agl-extra.git
  - Branch: "master"

* OpenEmbedded's `meta-openembedded` Layer:

  - URI: https://github.com/openembedded/meta-openembedded
  - Branch: "thud"
  - Tested Revision: See the [`default.xml`](https://github.com/leon-anavi/AGL-repo/blob/master/default.xml)
    manifest file for the `AGL-repo` repository for revision
    information.

    In particular, the `meta-openembedded` layer depends on the following
    sub-layers:

      - `meta-filesystems`
      - `meta-ruby`

* OpenEmbedded's `meta-rust` Layer:

  - URI: https://github.com/meta-rust/meta-rust
  - Branch: "thud"
  - Tested Revision: See the `[default.xml](https://github.com/leon-anavi/AGL-repo/blob/master/default.xml)`
    manifest file for the `AGL-repo` repository for revision
    information.

**The `agl-netboot` Feature:**

* AGL's `meta-netboot` Layer Within the `meta-agl` Layer:

  - URI: https://gerrit.automotivelinux.org/gerrit/gitweb?p=AGL/meta-agl.git
  - Branch: "master"


## Packagegroups

AGL DEMO Platform's 
[packagegroups](https://www.yoctoproject.org/docs/2.4.4/dev-manual/dev-manual.html#usingpoky-extend-customimage-customtasks)
consist of the following:

### packagegroup-agl-demo-platform

This packagegroup is used for generating the `agl-demo-platform` image,
which is the full image for the AGL distributions IVI profile.
You can see the recipe (i.e. `agl-demo-platform.bb`) that installs 
the `packagegroup-agl-demo-platform` packagegroup
[here](https://git.automotivelinux.org/AGL/meta-agl-demo/tree/recipes-platform/images/agl-demo-platform.bb).

As meta-agl's design of packagegroups, the `agl-demo-platform.bb` recipe installs
only `packagegroup-agl-demo-platform` and the packages of the DEMO applications.

``agl-demo-platform`` contains the following four packagegroups:

  * `packagegroup-agl-image-minimal`
  * `packagegroup-agl-image-ivi`
  * `packagegroup-ivi-common-core`
  * `packagegroup-agl-demo-platform`

### packagegroup-agl-appfw*

These packagegroups contain packages for the AGL distribution's
Application Framework.
Subsystem should maintain `packagegroup-agl-appfw-[subsystem].bb`, which
should hold sufficient packages for the Application Framework.

Subsystems also can maintain their own packagegroups using appropriate
`recipes-*/`.
For example, Qt5 has two packagegroups in `meta-agl-demo`:
`packagegroup-agl-appfw-native-qt5` and `packagegroup-agl-demo-qt-examples`,
which are under `recipes-qt/`.

The `packagegroup-agl-appfw-native-qt5` is included by
`packagegroup-agl-appfw-native` because Qt5 belongs to native application
framework of AGL Distro.

Because the `packagegroup-agl-demo-qt-examples` is not mandatory for
the AGL Application Framework and the AGL DEMO, the packagegroup is added
to the layer's `local.conf` file only when needed.

