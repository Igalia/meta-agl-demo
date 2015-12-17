TEMPLATE = lib
TARGET = ClimateControl
QT += qml quick dbus
CONFIG += qt plugin c++11
CONFIG(release):DEFINES += QT_NO_DEBUG_OUTPUT

TARGET = $$qtLibraryTarget($$TARGET)
uri = Automotive.ClimateControl

# Input
SOURCES += \
    climatecontrol_plugin.cpp \
    climatecontrol.cpp

HEADERS += \
    climatecontrol_plugin.h \
    climatecontrol.h

DISTFILES = qmldir \
    climatecontroltest.qml

!equals(_PRO_FILE_PWD_, $$OUT_PWD) {
    copy_qmldir.target = $$OUT_PWD/qmldir
    copy_qmldir.depends = $$_PRO_FILE_PWD_/qmldir
    copy_qmldir.commands = $(COPY_FILE) \"$$replace(copy_qmldir.depends, /, $$QMAKE_DIR_SEP)\" \"$$replace(copy_qmldir.target, /, $$QMAKE_DIR_SEP)\"
    QMAKE_EXTRA_TARGETS += copy_qmldir
    PRE_TARGETDEPS += $$copy_qmldir.target
}

qmldir.files = qmldir
unix {
    installPath = $$[QT_INSTALL_QML]/$$replace(uri, \\., /)
    qmldir.path = $$installPath
    target.path = $$installPath
    INSTALLS += target qmldir
}
