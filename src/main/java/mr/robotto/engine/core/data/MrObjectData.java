/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.data;

import java.util.HashMap;
import java.util.Map;

import mr.robotto.engine.components.comp.MrShaderProgram;
import mr.robotto.engine.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.linearalgebra.MrTransform;

/**
 * Created by aaron on 14/04/2015.
 */
public abstract class MrObjectData {
    protected String mName;
    protected MrSceneObjectType mSceneObjType;
    protected MrTransform mTransform;
    protected MrShaderProgram mShaderProgram;
    protected Map<String, MrUniformGenerator> mUniformGenerators;
    protected Map<String, MrUniformKey> mUniformKeys;

    public MrObjectData(String name, MrSceneObjectType sceneObjType, MrTransform transform, MrShaderProgram program, Map<String,MrUniformKey> uniformKeys) {
        mName = name;
        mTransform = transform;
        mSceneObjType = sceneObjType;
        mShaderProgram = program;
        mUniformKeys = uniformKeys;
        init();
    }

    //TODO: Review this constructor
    public MrObjectData(String name, MrSceneObjectType sceneObjType) {
        this(name, sceneObjType, new MrTransform(), null, new HashMap<String, MrUniformKey>());
    }

    private void init() {
        mUniformGenerators = new HashMap<>();
    }

    public void initializeUniforms() {

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

    public Map<String, MrUniformKey> getUniformKeys() {
        return mUniformKeys;
    }

    public void setUniformKeys(Map<String, MrUniformKey> uniformKeys) {
        this.mUniformKeys = uniformKeys;
    }

    public Map<String, MrUniformGenerator> getUniformGenerators() {
        return mUniformGenerators;
    }

    public void setUniformGenerators(Map<String, MrUniformGenerator> uniformGenerators) {
        mUniformGenerators = uniformGenerators;
    }
}
