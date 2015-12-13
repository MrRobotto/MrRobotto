/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.controller;

import java.util.Map;

import mr.robotto.engine.components.shader.MrShaderProgram;
import mr.robotto.engine.components.uniformgenerators.MrSceneUniformsGeneratorManager;
import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.data.MrSceneData;
import mr.robotto.engine.core.renderer.MrSceneRender;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.linearalgebra.MrVector4f;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrSceneController extends MrObjectController {

    public MrSceneController(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector4f clearColor) {
        super(createSceneData(name, transform, program, uniformKeys, clearColor), new MrSceneRender());
        init();
    }

    private static MrSceneData createSceneData(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector4f clearColor) {
        MrSceneData.Builder builder = new MrSceneData.Builder();
        builder
                .setClearColor(clearColor)
                .setName(name)
                .setTransform(transform)
                .setProgram(program)
                .setUniformKeys(uniformKeys);
        return builder.build();
    }

    private void init() {
        mObjectUniformsGenerators = new MrSceneUniformsGeneratorManager();
    }

    public MrVector4f getClearColor() {
        return ((MrSceneData) mData).getClearColor();
    }

    public void setClearColor(MrVector4f clearColor) {
        ((MrSceneData) mData).setClearColor(clearColor);
    }

    public void setClearColor(float r, float g, float b, float a) {
        ((MrSceneData) mData).setClearColor(r, g, b, a);
    }
}
