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

import mr.robotto.engine.components.MrShaderProgram;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.core.data.uniformgenerators.MrSceneUniformsGenerators;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.linearalgebra.MrVector4f;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrSceneData extends MrObjectData {
    private MrVector4f mClearColor;

    private MrSceneData(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector4f clearColor) {
        super(name, MrSceneObjectType.SCENE, transform, program, uniformKeys);
        mClearColor = clearColor;
        init();
    }

    private void init() {
        mObjectUniformsGenerators = new MrSceneUniformsGenerators();
    }

    public MrVector4f getClearColor() {
        return mClearColor;
    }

    public void setClearColor(MrVector4f clearColor) {
        mClearColor = clearColor;
    }

    public void setClearColor(float r, float g, float b, float a) {
        mClearColor = new MrVector4f(r, g, b, a);
    }

    public static class Builder extends MrObjectData.BuilderBase {
        private MrVector4f mclearColor = new MrVector4f(0.5f);

        public Builder setClearColor(MrVector4f clearColor) {
            mclearColor = clearColor;
            return this;
        }

        public MrSceneData build() {
            return new MrSceneData(mName, mTransform, mProgram, mUniformKeys, mclearColor);
        }
    }
}
