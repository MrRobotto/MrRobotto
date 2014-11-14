/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.controller;

import mr.robotto.renderer.core.data.scene.MrSceneData;
import mr.robotto.renderer.core.view.objectrenderers.MrSceneRender;

public class MrSceneController extends MrObjectController<MrSceneData, MrSceneRender> {
    public MrSceneController(MrSceneData data, MrSceneRender render) {
        super(data, render);
    }
}
