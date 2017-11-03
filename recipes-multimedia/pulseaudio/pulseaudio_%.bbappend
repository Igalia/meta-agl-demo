# Add .include directive to default.pa so optional configuration can be added
do_install_append () {
    echo -n "\n.include ${sysconfdir}/pulse/default.d\n" >> ${D}${sysconfdir}/pulse/default.pa
}
