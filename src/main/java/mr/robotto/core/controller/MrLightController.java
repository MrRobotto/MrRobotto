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
}
