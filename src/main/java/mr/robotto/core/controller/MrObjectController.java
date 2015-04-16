/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.renderer.MrObjectRender;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.renderer.MrRenderingContext;
import mr.robotto.renderer.uniformgenerator.MrUniformGeneratorMap;

/**
 * Created by aaron on 14/04/2015.
 */
public abstract class MrObjectController {
    protected MrObjectData mData;
    protected MrObjectRender mRender;

    protected boolean mInitialized;

    public MrObjectController(MrObjectData data, MrObjectRender render) {
        mData = data;
        mRender = render;
        mInitialized = false;
    }

    //TODO: Delete
    public MrObjectData getData() {
        return mData;
    }

    //TODO: initializeRender(Context, data)
    public void initializeRender(MrRenderingContext context) {
        mRender.initializeRender(context, mData);
        initializeUniforms(mData.getUniformGenerators());
        mInitialized = true;
    }

    //TODO: initializeSizeDependant(Context, w, h)
    public void initializeSizeDependant(int widthScreen, int heightScreen) {
        mRender.initializeSizeDependant(widthScreen, heightScreen);
    }

    //TODO: render(Context, data)
    public void render() {
        //if (!mInitialized) initializeRender(context, mData);
        mRender.render();
    }

    public boolean isInitialized() {
        return mInitialized;
    }

    public String getName() {
        return mData.getName();
    }

    public MrSceneObjectType getSceneObjectType() {
        return mData.getSceneObjectType();
    }

    public MrTransform getTransform() {
        return mData.getTransform();
    }

    public void setTransform(MrTransform transform) {
        mData.setTransform(transform);
    }

    public void initializeUniforms(MrUniformGeneratorMap uniformGenerators) {

    }

    public MrUniformGeneratorMap getUniformGenerators() {
        return mData.getUniformGenerators();
    }

    public MrShaderProgram getShaderProgram() {
        return mData.getShaderProgram();
    }

    public MrUniformKeyMap getUniformKeys() {
        return mData.getUniformKeys();
    }

}
