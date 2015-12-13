/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.components.uniformgenerators;

import java.util.Map;

import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.data.MrLightData;
import mr.robotto.engine.core.data.MrObjectData;
import mr.robotto.engine.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.engine.linearalgebra.MrVector3f;
import mr.robotto.engine.linearalgebra.MrVector4f;
import mr.robotto.engine.scenetree.MrObjectsDataTree;

/**
 * Created by aaron on 14/06/2015.
 */
public class MrLightUniformsGeneratorManager implements MrUniformsGeneratorManager {
    @Override
    public void initializeUniforms(MrObjectData object, Map<String, MrUniformKey.Generator> uniformGenerators) {
        MrLightData light = (MrLightData) object;
        uniformGenerators.put(MrUniformKey.GENERATOR_LIGHT_COLOR, new LightColorGenerator());
        uniformGenerators.put(MrUniformKey.GENERATOR_LIGHT_POSITION, new LightPositionGenerator());
    }

    private static class LightColorGenerator implements MrUniformKey.Generator {
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

    private static class LightPositionGenerator implements MrUniformKey.Generator {
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
}
