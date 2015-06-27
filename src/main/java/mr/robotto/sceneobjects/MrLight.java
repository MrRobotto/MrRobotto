/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.sceneobjects;

import java.util.Map;

import mr.robotto.engine.components.MrShaderProgram;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.controller.MrLightController;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.linearalgebra.MrVector3f;

/**
 * Created by aaron on 11/05/2015.
 */
public class MrLight extends MrObject {
    public MrLight(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector3f lightColor) {
        super(new MrLightController(name, transform, program, uniformKeys, lightColor));
    }

    public static class Builder extends MrObjectBuilder {
        private MrVector3f mLightColor;

        @Override
        public Builder setName(String name) {
            return (Builder) super.setName(name);
        }

        @Override
        public Builder setTransform(MrTransform transform) {
            return (Builder) super.setTransform(transform);
        }

        @Override
        public Builder setUniformKeys(Map<String, MrUniformKey> uniformKeys) {
            return (Builder) super.setUniformKeys(uniformKeys);
        }

        @Override
        public Builder setShaderProgram(MrShaderProgram shaderProgram) {
            return (Builder) super.setShaderProgram(shaderProgram);
        }

        public Builder setLightColor(MrVector3f lightColor) {
            mLightColor = lightColor;
            return this;
        }

        public MrLight createLight() {
            return new MrLight(mName, mTransform, mShaderProgram, mUniformKeys, mLightColor);
        }
    }
}
