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

import mr.robotto.engine.components.comp.MrShaderProgram;
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

    public MrSceneData(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector4f clearColor) {
        super(name, MrSceneObjectType.SCENE, transform, program, uniformKeys);
        mClearColor = clearColor;
    }

    public MrSceneData(String name, MrVector4f clearColor) {
        super(name, MrSceneObjectType.SCENE);
        mClearColor = clearColor;
        init();
    }

    public MrSceneData(String name) {
        this(name, new MrVector4f(0.5f));
    }

    private void init() {

    }

    @Override
    public void initializeUniforms() {
        super.initializeUniforms();
        new MrSceneUniformsGenerators().initializeUniforms(this, mUniformGenerators);
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
}
