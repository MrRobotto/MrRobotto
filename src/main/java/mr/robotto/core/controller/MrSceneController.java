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
import mr.robotto.components.data.shader.MrUniform;
import mr.robotto.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.data.MrSceneData;
import mr.robotto.core.renderer.MrSceneRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector4f;
import mr.robotto.scenetree.MrObjectsDataTree;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrSceneController extends MrObjectController {

    public MrSceneController(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector4f clearColor) {
        super(new MrSceneData(name, transform, program, uniformKeys, clearColor), new MrSceneRender());
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
