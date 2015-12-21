/* Copyright (C) 2015, Jaguar Land Rover. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

#include <QCoreApplication>
#include <QDBusConnection>
#include <QDBusInterface>
#include <QDBusObjectPath>
#include <QDBusReply>
#include <QDebug>
#include <string.h>

int main(int argc, char *argv[])
{
    // TODO: Clean this up, update API, etc.
    if (argc < 5 || argc > 6)
    {
        qWarning() << "Error detected! Insufficient number of arguments.";
        qWarning() << "";
        qWarning() << "Usage: ambdbusaccess <R/W> <Object> <Property> <Zone> [Value]";
        qWarning() << "- <R/W>";
        qWarning() << "  Used for specifying [R]ead or [W]rite.";
        qWarning() << "- <Object>";
        qWarning() << "  The AMB object to access.";
        qWarning() << "- <Property>";
        qWarning() << "  The property within the object to access.";
        qWarning() << "- <Zone>";
        qWarning() << "  The AMB zone to access.";
        qWarning() << "- [Value]";
        qWarning() << "  The value to write, if writing.";
        qWarning() << "Example: ambdbusaccess Write ClimateControl FanSpeedLevel 0 7";
        qWarning() << "";
        qWarning() << "This program returns an int under the following conditions:";
        qWarning() << "Successful Read: <Value Read>";
        qWarning() << "Unsuccessful Read: -1";
        qWarning() << "Successful Write: <Value Written>";
        qWarning() << "Unsuccessful Write: -1";

        return -1;
    }

    // TODO: Error check input.
    bool read = !strncmp(argv[1], "R", 1);
    QString object = argv[2];
    QString property = argv[3];
    qint32 zone = atoi(argv[4]);
    qint32 value = 0;

    if (argc == 6)
    {
        value = atoi(argv[5]);
    }

    // Necessary to suppress Qt messages about touching the D-Bus before the application was created.
    QCoreApplication a(argc, argv);

    // Sanity check that the system bus is actually present.
    if (!QDBusConnection::systemBus().isConnected())
    {
        qCritical() << "Could not access system D-Bus!";
        return -1;
    }

    // Get ahold of the manager object.
    QDBusInterface *manager = new QDBusInterface("org.automotive.message.broker", "/", "org.automotive.Manager",
                                                 QDBusConnection::systemBus());

    // Go fetch the path for the AMB object we are concerned with.
    qDebug().nospace() << "Looking for property " << property.toStdString().c_str() << " of object " <<
                          object.toStdString().c_str() << " in zone " << zone << ".";
    QDBusReply<QDBusObjectPath> propertyPath = manager->call("FindObjectForZone", object.toStdString().c_str(), zone);
    if (!propertyPath.isValid())
    {
        qDebug() << "Invalid reply!" << propertyPath.error() << "Got the path:" << propertyPath.value().path();
    }

    // Now that we have the path, open an interface to the object.
    QDBusInterface *propertyInterface = new QDBusInterface("org.automotive.message.broker", propertyPath.value().path(),
                                                           "org.automotive.ClimateControl", QDBusConnection::systemBus());

    // Perform our read or write operation.
    if (read)
    {
        QVariant reply = propertyInterface->property(property.toStdString().c_str());
        if (!reply.isValid())
        {
            qDebug() << "Invalid reply when reading the property!" << propertyInterface->lastError() << "Property:" <<
                        reply.toString();
            value = -1;
        }
        else
        {
            qDebug().nospace() << "Got a valid reply for the property of " << reply.toString().toStdString().c_str() << ".";
            value = reply.toInt();
        }
    }
    else
    {
        QVariant reply = propertyInterface->setProperty(property.toStdString().c_str(), value);
        if (reply.toBool())
        {
            qDebug() << "Successfully wrote the property.";
        }
        else
        {
            qDebug() << "Failed to write the property.";
            value = -1;
        }
    }

    // Clean up.
    delete propertyInterface;
    delete manager;

    // Either provide the read value or some feedback to the calling application.
    return value;
}

