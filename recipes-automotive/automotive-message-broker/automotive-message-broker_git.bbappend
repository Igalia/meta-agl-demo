inherit cmake_qt5

PACKAGECONFIG ??= "qmlplugins"

PACKAGECONFIG[qmlplugins] = "-Dqt_bindings=On,,qtdeclarative"

INSANE_SKIP_${PN} = "dev-deps"

FILES_${PN} += " \
    ${OE_QMAKE_PATH_QML}/amb/qmldir \
    ${OE_QMAKE_PATH_QML}/amb/libambqtquick.so \
"

FILES_${PN}-dbg += " \
    ${OE_QMAKE_PATH_QML}/amb/.debug/libambqtquick.so \
"
