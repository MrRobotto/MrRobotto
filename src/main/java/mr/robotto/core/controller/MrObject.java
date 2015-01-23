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
import mr.robotto.core.MrUniformGeneratorContainer;
import mr.robotto.core.data.commons.MrObjectData;
import mr.robotto.core.renderer.MrObjectRender;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.proposed.MrAction;
import mr.robotto.proposed.MrRenderingContext;

public abstract class MrObject {
    private MrObjectData mData;
    private MrObjectRender mRender;
    private MrRenderingContext mContext;
    private MrUniformGeneratorContainer mUniformGenerators;
    private Queue<MrAction<MrObjectData>> mActions;

    protected MrObject(MrObjectData data, MrObjectRender render, MrUniformGeneratorContainer uniformGenerator) {
        mData = data;
        mRender = render;
        mUniformGenerators = uniformGenerator;
    }

    protected MrObject(MrObjectData data, MrObjectRender render) {
        mData = data;
        mRender = render;
        mUniformGenerators = new MrUniformGeneratorContainer();
    }

    //TODO: Fill this to create objects in the loader
    protected MrObject(MrObject object) {

    }

    private void init() {
        mActions = new LinkedList<MrAction<MrObjectData>>();
    }

    public void initialize() {
        mRender.initializeRender(mData, mContext);
        initializeUniforms(mUniformGenerators);
    }

    public void initializeSizeDependant(int w, int h) {
        mRender.initializeSizeDependant(w, h);
    }

    public boolean isInitialized() {
        return mRender.isInitialized();
    }

    public MrRenderingContext getRenderingContext() {
        return mContext;
    }

    //TODO: Update render
    public void setRenderingContext(MrRenderingContext context) {
        mContext = context;
    }

    public MrUniformGeneratorContainer getUniformGenerators() {
        return mUniformGenerators;
    }

    public void setUniformGenerators(MrUniformGeneratorContainer uniformGenerators) {
        mUniformGenerators = uniformGenerators;
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

    //TODO: This should be abstract
    public MrSceneObjectType getSceneObjectType() {
        return mData.getSceneObjectType();
    }

    public MrTransform getTransform() {
        return mData.getTransform();
    }

    public void initializeUniforms(MrUniformGeneratorContainer uniformGenerators) {

    }

    public void addAction(final MrAction<MrObjectData> action) {
        mActions.add(action);
    }

    public Collection<MrAction<MrObjectData>> getActions() {
        return mActions;
    }
}
