/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import mr.robotto.core.MrSceneObjectType;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.renderer.MrObjectRender;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.proposed.MrAction;
import mr.robotto.renderer.MrRenderingContext;
import mr.robotto.renderer.uniformgenerator.MrUniformGeneratorMap;

public abstract class MrObject {
    private MrObjectData mData;
    private MrObjectRender mRender;
    private Queue<MrAction<MrObjectData>> mActions;

    protected MrObject(MrObjectData data, MrObjectRender render) {
        mData = data;
        mRender = render;
    }

    //TODO: Fill this to create objects in the loader
    protected MrObject(MrObject object) {

    }

    private void init() {
        mActions = new LinkedList<MrAction<MrObjectData>>();
    }

    public void initializeRender(MrRenderingContext context) {
        mRender.initializeRender(mData, context);
        initializeUniforms(mData.getUniformGenerators());
    }

    public void initializeSizeDependant(int widthScreen, int heightScreen) {
        mRender.initializeSizeDependant(widthScreen, heightScreen);
    }

    public boolean isInitialized() {
        return mRender.isInitialized();
    }

    public void render() {
        mRender.render();
    }

    public MrObjectData getData() {
        return mData;
    }

    public String getName() {
        return mData.getName();
    }

    public MrObjectRender getRender() {
        return mRender;
    }

    public void setRender(MrObjectRender render) {
        this.mRender = render;
    }

    public MrSceneObjectType getSceneObjectType() {
        return mData.getSceneObjectType();
    }

    public MrTransform getTransform() {
        return mData.getTransform();
    }

    public void initializeUniforms(MrUniformGeneratorMap uniformGenerators) {

    }

    public MrUniformGeneratorMap getUniformGenerators() {
        return mData.getUniformGenerators();
    }

    public void setUniformGenerators(MrUniformGeneratorMap uniformGenerators) {
        mData.setUniformGenerators(uniformGenerators);
    }

    public void addAction(final MrAction<MrObjectData> action) {
        mActions.add(action);
    }

    public Collection<MrAction<MrObjectData>> getActions() {
        return mActions;
    }
}
