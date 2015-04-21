/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.renderer;

import mr.robotto.components.data.mesh.MrBufferKey;
import mr.robotto.components.data.mesh.MrBufferKeyMap;
import mr.robotto.components.data.shader.MrAttribute;
import mr.robotto.core.data.MrModelData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.renderer.MrRenderingContext;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelRender implements MrObjectRender {
    private MrModelData mModelData;
    private MrRenderingContext mContext;
    private boolean mInitialized = false;
    private boolean mBinded = false;

    public MrModelRender() {
    }

    @Override
    public boolean isInitialized() {
        return mInitialized;
    }

    @Override
    public void initializeRender(MrRenderingContext context, MrObjectData link) {
        mContext = context;
        mModelData = (MrModelData) link;

        //Attribute index assignation to mesh
        MrBufferKeyMap keyMap = mModelData.getMesh().getBufferKeys();
        for (MrAttribute attribute : mModelData.getShaderProgram().getAttributes()) {
            MrBufferKey key = keyMap.findByKey(attribute.getAttributeType());
            key.setIndex(attribute.getIndex());
        }
        mModelData.getMesh().initialize();
        mModelData.getShaderProgram().initialize();
        mInitialized = true;
    }

    @Override
    public void initializeSizeDependant(int w, int h) {

    }

    @Override
    public void render() {
        bind();
        draw();
        unbind();
    }


    private void bind() {
        mModelData.getShaderProgram().bind();
        //Assign of uniforms to the shader program
        //mModelData.getShaderProgram().bindUniforms(mContext.getUniformGenerators());
        mModelData.getShaderProgram().bindUniforms(mContext.getUniforms());
        mModelData.getMesh().bind();
        mBinded = true;
    }

    private boolean isBinded() {
        return mBinded;
    }


    private void unbind() {
        mModelData.getMesh().unbind();
        mModelData.getShaderProgram().unbind();
        mBinded = false;
    }

    private void draw() {
        mModelData.getMesh().draw();
    }
}
