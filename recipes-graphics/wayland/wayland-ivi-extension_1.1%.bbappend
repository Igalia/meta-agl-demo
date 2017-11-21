FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

ENABLE_TEST = "-DBUILD_ILM_API_TESTS:BOOL=TRUE \
    -DINSTALL_ILM_API_TESTS:BOOL=TRUE \
    "

DISABLE_TEST = "-DBUILD_ILM_API_TESTS:BOOL=FALSE \
    -DINSTALL_ILM_API_TESTS:BOOL=FALSE \
    "

PACKAGECONFIG ??= "test"
PACKAGECONFIG[test] = "${ENABLE_TEST},${DISABLE_TEST},gtest"

SRC_URI_append = "\
    file://cmake-find-gtest.patch \
    file://test-path.patch \
    "

PACKAGES =+ "${PN}-test"

FILES_${PN}-test = "${libdir}/${PN}/test/*/CTestTestfile.cmake \
    ${libdir}/${PN}/test/ivi-layermanagement-api-test \
    ${libdir}/${PN}/test/ivi-input-api-test \
    "
FILES_${PN}-dbg += "${libdir}/${PN}/test/.debug"

RDEPENDS_${PN}-test += "cmake"
