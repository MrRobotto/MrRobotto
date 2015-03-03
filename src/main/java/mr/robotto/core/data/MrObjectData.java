/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data;

import mr.robotto.components.data.shader.MrShaderProgram;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.renderer.uniformgenerator.MrUniformGeneratorMap;

public abstract class MrObjectData {
    protected String mName;
    protected MrSceneObjectType mSceneObjType;
    protected MrTransform mTransform;
    protected MrShaderProgram mShaderProgram;
    protected MrUniformGeneratorMap mUniformGenerators;
    protected MrUniformKeyMap mUniformKeys;

    public MrObjectData(String name, MrSceneObjectType sceneObjType, MrTransform transform, MrShaderProgram program, MrUniformKeyMap uniformKeys) {
        mName = name;
        mTransform = transform;
        mSceneObjType = sceneObjType;
        mShaderProgram = program;
        mUniformKeys = uniformKeys;
        init();
    }

    //TODO: Remove these two constructors
    public MrObjectData(String name, MrSceneObjectType sceneObjType, MrTransform transform, MrUniformKeyMap uniformKeys) {
        mName = name;
        mTransform = transform;
        mSceneObjType = sceneObjType;
        mUniformKeys = uniformKeys;
        init();
    }

    //TODO: Review this constructor
    public MrObjectData(String name, MrSceneObjectType sceneObjType) {
        this(name, sceneObjType, new MrTransform(), new MrUniformKeyMap());
    }

    private void init() {
        mUniformGenerators = new MrUniformGeneratorMap();
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

    public MrShaderProgram getShaderProgram() {
        return mShaderProgram;
    }

    public MrUniformKeyMap getUniformKeys() {
        return mUniformKeys;
    }

    public void setUniformKeys(MrUniformKeyMap uniformKeys) {
        this.mUniformKeys = uniformKeys;
    }

    public MrUniformGeneratorMap getUniformGenerators() {
        return mUniformGenerators;
    }

    public void setUniformGenerators(MrUniformGeneratorMap uniformGenerators) {
        mUniformGenerators = uniformGenerators;
    }
}
