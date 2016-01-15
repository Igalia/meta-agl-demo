FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

APPLY_v522 = "${@str('no' if '${PV}' != '5.22' else 'yes')}"

SRC_URI_append = "\
    file://0001_fix_compile_issue_when_using_in_c++.patch;apply=${APPLY_v522} \
"
