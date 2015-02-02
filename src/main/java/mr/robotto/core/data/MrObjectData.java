/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data;

import mr.robotto.core.controller.uniformgenerator.MrUniformGeneratorContainer;
import mr.robotto.core.data.resources.uniformkey.MrUniformKeyContainer;
import mr.robotto.linearalgebra.MrTransform;

public abstract class MrObjectData {
    private String mName;
    private MrSceneObjectType mSceneObjType;
    private MrTransform mTransform;
    private MrUniformGeneratorContainer mUniformGenerators;
    private MrUniformKeyContainer mUniformKeys;

    public MrObjectData(String name, MrSceneObjectType sceneObjType, MrTransform transform, MrUniformKeyContainer uniformKeys) {
        mName = name;
        mTransform = transform;
        mSceneObjType = sceneObjType;
        mUniformKeys = uniformKeys;
        mUniformGenerators = new MrUniformGeneratorContainer();
        init();
    }

    //TODO: Review this constructor
    public MrObjectData(String name, MrSceneObjectType sceneObjType) {
        this(name, sceneObjType, new MrTransform(), new MrUniformKeyContainer());
    }

    private void init() {

    }

    public String getName() {
        return mName;
    }

    public MrSceneObjectType getSceneObjectType() {
        return mSceneObjType;
    }

    public MrTransform getTransform() {
        return mTransform;
    }

    public void setTransform(MrTransform transform) {
        this.mTransform = transform;
    }

    public MrUniformKeyContainer getUniformKeys() {
        return mUniformKeys;
    }

    public void setUniformKeys(MrUniformKeyContainer uniformKeys) {
        this.mUniformKeys = uniformKeys;
    }

    public MrUniformGeneratorContainer getUniformGenerators() {
        return mUniformGenerators;
    }

    public void setUniformGenerators(MrUniformGeneratorContainer uniformGenerators) {
        mUniformGenerators = uniformGenerators;
    }
}
