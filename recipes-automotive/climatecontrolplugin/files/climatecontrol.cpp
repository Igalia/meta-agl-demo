/* Copyright (C) 2015, Jaguar Land Rover. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

#include <QDBusConnection>
#include <QDBusObjectPath>
#include <QDBusReply>
#include <QDebug>

#include "climatecontrol.h"

ClimateControl::ClimateControl(QQuickItem *parent):
    QQuickItem(parent)
{
    qDebug() << "ClimateControl plugin loaded!";

    //
    // Verify that we can access the system bus, where AMB resides. This is largely informational, since the ambdbusaccess
    // application is responsible for actually accessing AMB.
    //
    if (!QDBusConnection::systemBus().isConnected())
    {
        qDebug() << "Unable to connect to system bus.";
    }
}

ClimateControl::~ClimateControl()
{
    qDebug() << "ClimateControl plugin unloaded.";
}

// Accessor methods.
qint32 ClimateControl::getZone()
{
    return this->zone;
}

void ClimateControl::setZone(qint32 newZone)
{
    if (this->zone != newZone)
    {
        this->zone = newZone;

        qDebug().nospace() << "Updated zone to " << this->zone << ".";

        emit zoneChanged();

        // Now that the zone has been updated, fetch the settings for this new zone.
        this->getSettings();
    }
}

quint16 ClimateControl::getFanSpeedLevel()
{
    return this->fanSpeedLevel;
}

void ClimateControl::setFanSpeedLevel(quint16 newFanSpeedLevel)
{
    if (this->fanSpeedLevel != newFanSpeedLevel)
    {
        this->dbusWrite("FanSpeedLevel", newFanSpeedLevel);
        this->fanSpeedLevel = newFanSpeedLevel;

        qDebug().nospace() << "Updated fan speed level to " << this->fanSpeedLevel << ".";

        emit fanSpeedLevelChanged();
    }
}

qint32 ClimateControl::getTargetTemperature()
{
    return this->targetTemperature;
}

void ClimateControl::setTargetTemperature(qint32 newTargetTemperature)
{
    if (this->targetTemperature != newTargetTemperature)
    {
        this->dbusWrite("TargetTemperature", newTargetTemperature);
        this->targetTemperature = newTargetTemperature;

        qDebug().nospace() << "Updated target temperature to " << this->targetTemperature << ".";

        emit targetTemperatureChanged();
    }
}

bool ClimateControl::getAirConditioning()
{
    return this->airConditioning;
}

void ClimateControl::setAirConditioning(bool newAirConditioning)
{
    if (this->airConditioning != newAirConditioning)
    {
        // NOTE: This property is not enabled in the current version of AMB, so no D-Bus access is performed.
        this->airConditioning = newAirConditioning;

        qDebug().nospace() << "Updated air conditioning to " << this->airConditioning << ".";

        emit airConditioningChanged();
    }
}

bool ClimateControl::getHeater()
{
    return this->heater;
}

void ClimateControl::setHeater(bool newHeater)
{
    if (this->heater != newHeater)
    {
        // NOTE: This property is not enabled in the current version of AMB, so no D-Bus access is performed.
        this->heater = newHeater;

        qDebug().nospace() << "Updated heater to " << this->heater << ".";

        emit heaterChanged();
    }
}

quint8 ClimateControl::getSeatHeater()
{
    return this->seatHeater;
}

void ClimateControl::setSeatHeater(quint8 newSeatHeater)
{
    if (this->seatHeater != newSeatHeater)
    {
        // NOTE: This property is not enabled in the current version of AMB, so no D-Bus access is performed.
        this->seatHeater = newSeatHeater;

        qDebug().nospace() << "Updated seat heater to " << this->seatHeater << ".";

        emit seatHeaterChanged();
    }
}

quint8 ClimateControl::getSeatCooler()
{
    return this->seatCooler;
}

void ClimateControl::setSeatCooler(quint8 newSeatCooler)
{
    if (this->seatCooler != newSeatCooler)
    {
        // NOTE: This property is not enabled in the current version of AMB, so no D-Bus access is performed.
        this->seatCooler = newSeatCooler;

        qDebug().nospace() << "Updated seat cooler to " << this->seatCooler << ".";

        emit seatCoolerChanged();
    }
}

bool ClimateControl::getAirCirculation()
{
    return this->airCirculation;
}

void ClimateControl::setAirCirculation(bool newAirCirculation)
{
    if (this->airCirculation != newAirCirculation)
    {
        // NOTE: This property is not enabled in the current version of AMB, so no D-Bus access is performed.
        this->airCirculation = newAirCirculation;

        qDebug().nospace() << "Updated air circulation to " << this->airCirculation << ".";

        emit airCirculationChanged();
    }
}

quint8 ClimateControl::getSteeringWheelHeater()
{
    return this->steeringWheelHeater;
}

void ClimateControl::setSteeringWheelHeater(quint8 newSteeringWheelHeater)
{
    if (this->steeringWheelHeater != newSteeringWheelHeater)
    {
        // NOTE: This property is not enabled in the current version of AMB, so no D-Bus access is performed.
        this->steeringWheelHeater = newSteeringWheelHeater;

        qDebug().nospace() << "Updated steering wheel heater to " << this->steeringWheelHeater << ".";

        emit steeringWheelHeaterChanged();
    }
}

// D-Bus connection methods.
QVariant ClimateControl::dbusRead(const char *property)
{
    QString arguments = "R ClimateControl " + QString::fromUtf8(property);
    QString zoneString;

    // The zone has not been set yet, so do not bother reading from AMB with the exception of FanSpeedLevel at zone 0.
    if (!this->zone && strcmp(property, "FanSpeedLevel"))
    {
        // Return something resembling an uninitialized variable.
        return 0;
    }

    // The FanSpeedLevel property is the only property at zone 0 for now.
    zoneString = QString::number(!strcmp(property, "FanSpeedLevel") ? 0 : this->zone);

    // Add in the zone information.
    arguments += " " + zoneString;

    qDebug() << "Calling ambdbusaccess with the following arguments:" << arguments;

    // system() returns the result in the upper 8 bits, so shift the result to the right by 8.
    return system(QString("/usr/lib/qt5/qml/Automotive/ambdbusaccess " + arguments).toStdString().c_str()) >> 8;
}

void ClimateControl::dbusWrite(const char *property, QVariant value)
{
    QString arguments = "W ClimateControl " + QString::fromUtf8(property);
    QString zoneString;

    // The zone has not been set yet, so do not bother writing to AMB with the exception of FanSpeedLevel at zone 0.
    if (!this->zone && strcmp(property, "FanSpeedLevel"))
    {
        return;
    }

    // The FanSpeedLevel property is the only property at zone 0 for now.
    zoneString = QString::number(!strcmp(property, "FanSpeedLevel") ? 0 : this->zone);

    // Add in the zone information and value to write.
    arguments += " " + zoneString + " " + value.toString();

    qDebug() << "Calling ambdbusaccess with the following arguments:" << arguments;

    system(QString("/usr/lib/qt5/qml/Automotive/ambdbusaccess " + arguments).toStdString().c_str());
}

void ClimateControl::getSettings()
{
    quint8 ambFanSpeedLevel;
    qint32 ambTargetTemperature;

    //
    // Go fetch the settings for this zone.
    //
    // NOTE: Other properties are not enabled in the current version of AMB, so no D-Bus access is performed for them.
    //
    ambFanSpeedLevel = this->dbusRead("FanSpeedLevel").toInt();
    ambTargetTemperature = this->dbusRead("TargetTemperature").toInt();

    // Call the set methods to generate Qt signals, if any are necessary.
    this->setFanSpeedLevel(ambFanSpeedLevel);
    this->setTargetTemperature(ambTargetTemperature);
}
