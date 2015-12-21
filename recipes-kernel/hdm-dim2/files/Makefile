# Makefile
#

SRC := $(shell pwd)

obj-m += hdm_dim2.o
hdm_dim2-y := dim2_hdm.o dim2_hal.o dim2_sysfs.o
CFLAGS_dim2_hdm.o := -I$(src)/../../mostcore/files -I$(src)/../../aim-network/files

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) clean

