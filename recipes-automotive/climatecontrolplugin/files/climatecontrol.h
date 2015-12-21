/* Copyright (C) 2015, Jaguar Land Rover. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

#ifndef CLIMATECONTROL_H
#define CLIMATECONTROL_H

#include <QQuickItem>

class ClimateControl : public QQuickItem
{
    Q_OBJECT
    Q_DISABLE_COPY(ClimateControl)
    Q_PROPERTY(int zone READ getZone WRITE setZone)
    Q_PROPERTY(int fanSpeedLevel READ getFanSpeedLevel WRITE setFanSpeedLevel)
    Q_PROPERTY(int targetTemperature READ getTargetTemperature WRITE setTargetTemperature)
    Q_PROPERTY(bool airConditioning READ getAirConditioning WRITE setAirConditioning)
    Q_PROPERTY(bool heater READ getHeater WRITE setHeater)
    Q_PROPERTY(int seatHeater READ getSeatHeater WRITE setSeatHeater)
    Q_PROPERTY(int seatCooler READ getSeatCooler WRITE setSeatCooler)
    Q_PROPERTY(bool airCirculation READ getAirCirculation WRITE setAirCirculation)
    Q_PROPERTY(int steeringWheelHeater READ getSteeringWheelHeater WRITE setSteeringWheelHeater)

public:
    ClimateControl(QQuickItem *parent = 0);
    ~ClimateControl();

    // Accessor functions.
    qint32 getZone();
    void setZone(qint32 newZone);
    quint16 getFanSpeedLevel();
    void setFanSpeedLevel(quint16 newFanSpeedLevel);
    qint32 getTargetTemperature();
    void setTargetTemperature(qint32 newTargetTemperature);
    bool getAirConditioning();
    void setAirConditioning(bool newAirConditioning);
    bool getHeater();
    void setHeater(bool newHeater);
    quint8 getSeatHeater();
    void setSeatHeater(quint8 newSeatHeater);
    quint8 getSeatCooler();
    void setSeatCooler(quint8 newSeatCooler);
    bool getAirCirculation();
    void setAirCirculation(bool newAirCirculation);
    quint8 getSteeringWheelHeater();
    void setSteeringWheelHeater(quint8 newSteeringWheelHeater);

signals:
    // Standard notifiers for property updates.
    void zoneChanged();
    void fanSpeedLevelChanged();
    void targetTemperatureChanged();
    void airConditioningChanged();
    void heaterChanged();
    void seatHeaterChanged();
    void seatCoolerChanged();
    void airCirculationChanged();
    void steeringWheelHeaterChanged();

private:
    // Representations of the AMB properties.
    qint32 zone;
    quint16 fanSpeedLevel;
    qint32 targetTemperature;
    bool airConditioning;
    bool heater;
    quint8 seatHeater;
    quint8 seatCooler;
    bool airCirculation;
    quint8 steeringWheelHeater;

    //
    // D-Bus functionality member variables.
    //
    // TODO: Make some of this a generic class for use by other plugins.
    //
    QVariant dbusRead(const char *property);
    void dbusWrite(const char *property, QVariant value);
    void getSettings();
};

#endif // CLIMATECONTROL_H
