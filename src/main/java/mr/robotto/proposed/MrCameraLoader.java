/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import android.util.JsonReader;

import mr.robotto.core.data.MrCameraData;

/**
 * Created by aaron on 02/03/2015.
 */
public class MrCameraLoader extends MrReaderBaseLoader<MrCameraData> {
    protected MrCameraLoader(JsonReader reader) {
        super(reader);
    }

    @Override
    public MrCameraData parse() {
        return null;
    }
}
