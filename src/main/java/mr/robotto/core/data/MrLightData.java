/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data;

import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector4f;

/**
 * Created by aaron on 11/05/2015.
 */
public class MrLightData extends MrObjectData {

    private MrVector4f mColor;

    public MrLightData(String name, MrTransform transform, MrShaderProgram program, MrUniformKeyMap uniformKeys, MrVector4f lightColor) {
        super(name, MrSceneObjectType.LIGHT, transform, program, uniformKeys);
        mColor = lightColor;
    }

    public MrVector4f getColor() {
        return mColor;
    }

    public void setColor(MrVector4f color) {
        mColor = color;
    }

    public void setColor(float r, float g, float b, float a) {
        mColor.setValues(r,g,b,a);
    }
}
