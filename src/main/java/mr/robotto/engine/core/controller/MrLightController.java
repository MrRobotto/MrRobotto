/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.controller;

import mr.robotto.engine.components.uniformgenerator.MrLightUniformsGeneratorManager;
import mr.robotto.engine.core.data.MrLightData;
import mr.robotto.engine.core.renderer.MrLightRender;

/**
 * Created by aaron on 11/05/2015.
 */
public class MrLightController extends MrObjectController {

    public MrLightController(MrLightData lightData, MrLightRender lightRender) {
        super(lightData, lightRender);
        mObjectUniformsGenerators = new MrLightUniformsGeneratorManager();
    }
}
