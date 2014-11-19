/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.object;

import mr.robotto.renderer.core.data.commons.MrSceneObjType;
import mr.robotto.renderer.proposed.MrIdentificable;
import mr.robotto.renderer.core.data.object.keys.MrUniformKeyList;

public abstract class MrObjectData implements MrIdentificable<String> {

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

    @Override
    public String getElementId() {
        return getName();
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
