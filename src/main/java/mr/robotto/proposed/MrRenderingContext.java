/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;


import mr.robotto.core.controller.uniformgenerator.MrUniformGeneratorContainer;
import mr.robotto.scenetree.MrSceneObjectsTree;

/**
 * Created by Aarón on 28/11/2014.
 */
public class MrRenderingContext {
    private MrSceneObjectsTree mObjectsTree;
    private MrUniformGeneratorContainer mUniformGenerators;

    public MrRenderingContext(MrSceneObjectsTree objectsTree) {
        mObjectsTree = objectsTree;
        mUniformGenerators = new MrUniformGeneratorContainer();
    }

    public MrSceneObjectsTree getObjectsTree() {
        return mObjectsTree;
    }

    public MrUniformGeneratorContainer getUniformGenerators() {
        return mUniformGenerators;
    }

}
