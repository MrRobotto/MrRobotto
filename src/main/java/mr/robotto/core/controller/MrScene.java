/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import mr.robotto.core.data.scene.MrSceneData;
import mr.robotto.core.renderer.MrObjectRender;

public class MrScene extends MrObject<MrSceneData> {
    public MrScene(MrSceneData data, MrObjectRender render) {
        super(data, render);
    }

    public void initializeSizeDependant(int width, int height) {

    }
}
