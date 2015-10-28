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
import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.data.MrLightData;
import mr.robotto.engine.core.renderer.MrLightRender;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.linearalgebra.MrVector3f;

/**
 * Created by aaron on 11/05/2015.
 */
public class MrLightController extends MrObjectController {
    public MrLightController(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector3f lightColor) {
        super(createLight(name, transform, program, uniformKeys, lightColor), new MrLightRender());
    }

    private static MrLightData createLight(String name, MrTransform transform, MrShaderProgram program, Map<String, MrUniformKey> uniformKeys, MrVector3f lightColor) {
        MrLightData.Builder builder = new MrLightData.Builder();
        builder
                .setColor(lightColor)
                .setName(name)
                .setTransform(transform)
                .setProgram(program)
                .setUniformKeys(uniformKeys);
        return builder.build();
    }
}
