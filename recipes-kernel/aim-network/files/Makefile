# Makefile
#

SRC := $(shell pwd)

obj-m := aim_network.o
aim_network-y := networking.o
CFLAGS_networking.o := -I$(src)/../../mostcore/files/

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) clean

