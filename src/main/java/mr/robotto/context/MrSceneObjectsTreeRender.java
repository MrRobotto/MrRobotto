/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.context;

import mr.robotto.core.controller.MrObject;
import mr.robotto.core.data.MrSceneObjectType;

/**
 * Created by Aarón on 18/01/2015.
 */
public class MrSceneObjectsTreeRender {

    private MrSceneObjectsTree mSceneObjectsTree;

    public MrSceneObjectsTreeRender(MrSceneObjectsTree sceneObjectsTree) {
        mSceneObjectsTree = sceneObjectsTree;
    }

    public void initialize() {
        for (MrObject model : mSceneObjectsTree) {
            model.initialize();
        }
    }

    public void render() {
        for (MrObject scene : mSceneObjectsTree.getByType(MrSceneObjectType.SCENE)) {
            scene.render();
            for (MrObject model : mSceneObjectsTree.getByType(MrSceneObjectType.MODEL)) {
                model.render();
            }
        }
    }
}
