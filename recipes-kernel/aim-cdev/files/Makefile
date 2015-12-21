# Makefile
#

SRC := $(shell pwd)

obj-m += aim_cdev.o
aim_cdev-y := cdev.o
CFLAGS_cdev.o := -I$(src)/../../mostcore/files/

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) clean

