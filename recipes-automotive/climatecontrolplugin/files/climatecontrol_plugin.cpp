/* Copyright (C) 2015, Jaguar Land Rover. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

#include "climatecontrol_plugin.h"
#include "climatecontrol.h"

#include <qqml.h>

void ClimateControlPlugin::registerTypes(const char *uri)
{
    Q_ASSERT(uri == QLatin1String("Automotive.ClimateControl"));

    // @uri Automotive.ClimateControl
    qmlRegisterType<ClimateControl>(uri, 1, 0, "ClimateControlItem");
}
