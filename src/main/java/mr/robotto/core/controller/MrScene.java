/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import mr.robotto.core.data.MrSceneData;
import mr.robotto.core.rendereable.objectrenderers.MrSceneRender;

public class MrScene extends MrObject<MrSceneData, MrSceneRender> {
    public MrScene(MrSceneData data, MrSceneRender render) {
        super(data, render);
    }

    public void initializeSizeDependant(int width, int height) {

    }

    /*public MrObjectData getObject(String name) {
        return objects.get(name);
    }

    public MrModelData getModel(String name) {
        return models.get(name);
    }*/
}
