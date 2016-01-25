/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.data;

import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.linearalgebra.MrVector3f;

/**
 * Created by aaron on 11/05/2015.
 */
public class MrLightData extends MrObjectData {

    private MrVector3f mColor;

    public MrLightData(String name) {
        super(name, MrSceneObjectType.LIGHT);
    }

    public MrVector3f getColor() {
        return mColor;
    }

    public void setColor(MrVector3f color) {
        mColor = color;
    }

    public void setColor(float r, float g, float b) {
        mColor.setValues(r, g, b);
    }
}
