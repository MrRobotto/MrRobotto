/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core;

import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.controller.MrLightController;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector3f;
import mr.robotto.linearalgebra.MrVector4f;

/**
 * Created by aaron on 11/05/2015.
 */
public class MrLight extends MrObject {
    public MrLight(String name, MrTransform transform, MrShaderProgram program, MrUniformKeyMap uniformKeys, MrVector3f lightColor) {
        super(new MrLightController(name, transform, program, uniformKeys, lightColor));
    }


}
