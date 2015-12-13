/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.data;

import java.util.Map;

import mr.robotto.engine.components.shader.MrShaderProgram;
import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.linearalgebra.MrVector3f;

/**
 * Created by aaron on 11/05/2015.
 */
public class MrLightData extends MrObjectData {

    private MrVector3f mColor;

    private MrLightData(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector3f lightColor) {
        super(name, MrSceneObjectType.LIGHT, transform, program, uniformKeys);
        mColor = lightColor;
        init();
    }

    private void init() {

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

    public static class Builder extends MrObjectData.BuilderBase {
        private MrVector3f mColor;

        public Builder setColor(MrVector3f color) {
            mColor = color;
            return this;
        }

        public MrLightData build() {
            return new MrLightData(mName, mTransform, mProgram, mUniformKeys, mColor);
        }
    }
}
