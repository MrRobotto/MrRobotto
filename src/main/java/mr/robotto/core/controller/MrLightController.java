/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import java.util.Map;

import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.data.MrLightData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.renderer.MrLightRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector3f;
import mr.robotto.linearalgebra.MrVector4f;
import mr.robotto.scenetree.MrObjectsDataTree;

/**
 * Created by aaron on 11/05/2015.
 */
public class MrLightController extends MrObjectController {
    public MrLightController(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector3f lightColor) {
        super(new MrLightData(name, transform, program, uniformKeys, lightColor), new MrLightRender());
    }

    private static class LightColorGenerator extends MrUniformGenerator {
        private final MrVector4f mLightColor;

        public LightColorGenerator() {
            mLightColor = new MrVector4f();
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            MrLightData light = (MrLightData) object;
            MrVector3f color = light.getColor();
            mLightColor.setValues(color.x, color.y, color.z, 0.0f);
            return mLightColor;
        }
    }

    private static class LightPositionGenerator extends MrUniformGenerator {
        private final MrVector4f mLightPosition;

        public LightPositionGenerator() {
            mLightPosition = new MrVector4f();
        }

        @Override
        public MrLinearAlgebraObject generateUniform(MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms, MrObjectData object) {
            MrLightData light = (MrLightData) object;
            MrVector3f pos = light.getTransform().getLocation();
            mLightPosition.setValues(pos.x, pos.y, pos.z, 1.0f);
            return mLightPosition;
        }
    }

    @Override
    public void initializeUniforms(Map<String, MrUniformGenerator> uniformGenerators) {
        super.initializeUniforms(uniformGenerators);
        uniformGenerators.put(MrUniformGenerator.UNIFORMGENERATOR_LIGHT_COLOR, new LightColorGenerator());
        uniformGenerators.put(MrUniformGenerator.UNIFORMGENERATOR_LIGHT_POSITION, new LightPositionGenerator());
    }
}
