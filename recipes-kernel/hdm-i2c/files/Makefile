# Makefile
#

SRC := $(shell pwd)

obj-m += hdm_i2c.o
CFLAGS_hdm_i2c.o := -I$(src)/../../mostcore/files

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) clean

