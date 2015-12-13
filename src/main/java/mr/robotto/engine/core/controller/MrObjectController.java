/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.controller;

import java.util.Map;
import java.util.Set;

import mr.robotto.engine.components.shader.MrShaderProgram;
import mr.robotto.engine.components.uniformgenerators.MrUniformsGeneratorManager;
import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.core.data.MrObjectData;
import mr.robotto.engine.core.renderer.MrObjectRender;
import mr.robotto.engine.events.MrDefaultEventListener;
import mr.robotto.engine.events.MrEventsListener;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.renderer.MrRenderingContext;
import mr.robotto.engine.scenetree.MrObjectsDataTree;
import mr.robotto.sceneobjects.MrObject;

/**
 * Created by aaron on 14/04/2015.
 */
public abstract class MrObjectController {
    protected MrObject mAttachedObject;
    protected MrObjectData mData;
    protected MrObjectRender mRender;
    protected MrUniformsGeneratorManager mObjectUniformsGenerators;
    protected MrEventsListener mEventsListener;

    protected boolean mInitialized;

    protected MrObjectController(MrObjectData data, MrObjectRender render) {
        mData = data;
        mRender = render;
        mAttachedObject = null;
        mInitialized = false;
        setEventsListener(new MrDefaultEventListener());
    }

    protected MrObjectController(MrObjectData data) {
        this(data, null);
    }

    protected void setRender(MrObjectRender render) {
        mRender = render;
    }

    //TODO: Delete
    public MrObjectData getData() {
        return mData;
    }

    protected void setData(MrObjectData data) {
        mData = data;
    }

    public MrEventsListener getEventsListener() {
        return mEventsListener;
    }

    public void setEventsListener(MrEventsListener eventsListener) {
        mEventsListener = eventsListener;
        mEventsListener.setObjectController(this);
    }

    public boolean isEventRegistered(String evName) {
        return mEventsListener.isEventRegistered(evName);
    }

    public Set<String> getRegisteredEvents() {
        return mEventsListener.getRegisteredEvents();
    }

    public void registerEvent(String eventName) {
        mEventsListener.registerEvent(eventName);
    }

    public void unregisterEvent(String eventName) {
        mEventsListener.unregisterEvent(eventName);
    }

    public MrObject getAttachedObject() {
        return mAttachedObject;
    }

    public void setAttachedObject(MrObject object) {
        mAttachedObject = object;
    }

    public final void updateUniform(MrUniformKey uniformKey, MrObjectsDataTree tree, Map<String, MrUniformKey> uniforms) {
        uniformKey.generate(tree, uniforms, mData);
    }

    //TODO: initializeRender(Context, data)
    public void initializeRender(MrRenderingContext context) {
        if (mRender != null) {
            mRender.initializeRender(context, mData);
            initializeUniforms();
        }
        mInitialized = true;
    }

    public void updateEvents() {
        mEventsListener.updateEvents();
    }

    //TODO: initializeSizeDependant(Context, w, h)
    public void initializeSizeDependant(int widthScreen, int heightScreen) {
        mRender.initializeSizeDependant(widthScreen, heightScreen);
    }

    //TODO: render(Context, data)
    public synchronized void render() {
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

    public void initializeUniforms() {
        Map<String, MrUniformKey.Generator> uniformGenerators = mData.getUniformGenerators();
        Map<String, MrUniformKey> uniformKeys = mData.getUniformKeys();
        mObjectUniformsGenerators.initializeUniforms(mData, uniformGenerators);
        for (MrUniformKey key : uniformKeys.values()) {
            MrUniformKey.Generator generator = uniformGenerators.get(key.getGeneratorName());
            key.setGenerator(generator);
        }
    }

    public MrShaderProgram getShaderProgram() {
        return mData.getShaderProgram();
    }

    public Map<String, MrUniformKey.Generator> getUniformGenerators() {
        return mData.getUniformGenerators();
    }

    public void addUniformGenerators(String uniformGeneratorName, MrUniformKey.Generator generator) {
        mData.addUniformGenerators(uniformGeneratorName, generator);
    }

    public Map<String, MrUniformKey> getUniformKeys() {
        return mData.getUniformKeys();
    }

    @Override
    public String toString() {
        return "MrObjectController{" +
                "name=" + getName() +
                ", type=" + getSceneObjectType() +
                '}';
    }
}
