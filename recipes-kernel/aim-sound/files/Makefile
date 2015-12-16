# Makefile
#

SRC := $(shell pwd)

obj-m += aim_sound.o
aim_sound-y := sound.o
CFLAGS_sound.o := -I$(src)/../../mostcore/files/

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) clean

