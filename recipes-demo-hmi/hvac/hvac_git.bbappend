# for demo
PACKAGE_ARCH_m3ulcb = "${MACHINE_ARCH}"
do_configure_prepend_m3ulcb() {
sed -i -e "s#vcan0#sllin0#g" ${S}/binding/hvac-demo-binding.c
}
