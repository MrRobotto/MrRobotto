/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data;

import mr.robotto.renderer.core.data.resources.MrTransform;
import mr.robotto.renderer.core.data.resources.commons.MrSceneObjType;
import mr.robotto.renderer.core.data.resources.uniformkeys.MrUniformKeyList;

public abstract class MrObjectData {

    private boolean mInitialized;

    private String mName;
    private MrSceneObjType mSceneObjType;
    private MrTransform mTransform;
    private MrUniformKeyList mUniformKeys;

    public MrObjectData(String name, MrSceneObjType sceneObjType, MrTransform transform, MrUniformKeyList uniformKeys) {
        mName = name;
        mTransform = transform;
        mSceneObjType = sceneObjType;
        mUniformKeys = uniformKeys;
        init();
    }

    //TODO: Review this constructor
    public MrObjectData(String name, MrSceneObjType sceneObjType) {
        this(name, sceneObjType, new MrTransform(), new MrUniformKeyList());
    }

    private void init() {
        mInitialized = false;
    }

    public String getName() {
        return mName;
    }

    public boolean isInitialized() {
        return mInitialized;
    }

    public void initialize() {
        mInitialized = true;
    }

    public void deInitialize() {
        mInitialized = false;
    }

    public MrSceneObjType getSceneObjType() {
        return mSceneObjType;
    }

    public MrTransform getTransform() {
        return mTransform;
    }

    public void setTransform(MrTransform transform) {
        this.mTransform = transform;
    }

    public MrUniformKeyList getUniformKeys() {
        return mUniformKeys;
    }

    public void setUniformKeys(MrUniformKeyList uniformKeys) {
        this.mUniformKeys = uniformKeys;
    }
}
