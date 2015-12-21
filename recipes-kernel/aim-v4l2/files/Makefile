# Makefile
#

SRC := $(shell pwd)

obj-m += aim_v4l2.o
aim_v4l2-y := video.o
CFLAGS_video.o := -Idrivers/media/video -I$(src)/../../mostcore/files/

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) clean

