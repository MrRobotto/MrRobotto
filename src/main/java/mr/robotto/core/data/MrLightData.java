/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data;

import java.util.Map;

import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.core.data.uniformgenerators.MrLightUniformsGenerators;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector3f;

/**
 * Created by aaron on 11/05/2015.
 */
public class MrLightData extends MrObjectData {

    private MrVector3f mColor;

    public MrLightData(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector3f lightColor) {
        super(name, MrSceneObjectType.LIGHT, transform, program, uniformKeys);
        mColor = lightColor;
    }

    @Override
    public void initializeUniforms() {
        super.initializeUniforms();
        new MrLightUniformsGenerators().initializeUniforms(this, mUniformGenerators);
    }

    public MrVector3f getColor() {
        return mColor;
    }

    public void setColor(MrVector3f color) {
        mColor = color;
    }

    public void setColor(float r, float g, float b) {
        mColor.setValues(r,g,b);
    }
}
