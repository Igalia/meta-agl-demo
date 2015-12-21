QT += core dbus
QT -= gui

TARGET = ambdbusaccess
CONFIG += console
CONFIG -= app_bundle
CONFIG(release):DEFINES += QT_NO_DEBUG_OUTPUT

TEMPLATE = app

SOURCES += \
    ambdbusaccess.cpp
