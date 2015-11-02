FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = "\
    file://0001_fix_compile_issue_when_using_in_c++.patch \
"
