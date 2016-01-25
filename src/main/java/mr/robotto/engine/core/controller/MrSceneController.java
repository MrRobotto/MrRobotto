/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.controller;

import mr.robotto.engine.components.uniformgenerator.MrSceneUniformsGeneratorManager;
import mr.robotto.engine.core.data.MrSceneData;
import mr.robotto.engine.core.renderer.MrSceneRender;
import mr.robotto.engine.linearalgebra.MrVector4f;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrSceneController extends MrObjectController {

    public MrSceneController(MrSceneData sceneData, MrSceneRender sceneRender) {
        super(sceneData, sceneRender);
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
