/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.renderer;

import java.util.Map;

import mr.robotto.engine.components.MrTexture;
import mr.robotto.engine.components.data.mesh.MrBufferKey;
import mr.robotto.engine.components.data.shader.MrAttribute;
import mr.robotto.engine.core.data.MrModelData;
import mr.robotto.engine.core.data.MrObjectData;
import mr.robotto.engine.renderer.MrRenderingContext;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelRender implements MrObjectRender {
    private MrModelData mModelData;
    private MrRenderingContext mRenderingContext;
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
        mRenderingContext = context;
        mModelData = (MrModelData) link;

        //Attribute index assignation to mesh
        Map<Integer, MrBufferKey> keyMap = mModelData.getMesh().getBufferKeys();
        for (MrAttribute attribute : mModelData.getShaderProgram().getAttributes().values()) {
            MrBufferKey key = keyMap.get(attribute.getAttributeType());
            key.setId(attribute.getIndex());
        }
        mModelData.getMesh().initialize(mRenderingContext);
        mModelData.getShaderProgram().initialize(mRenderingContext);

        for (MrTexture texture : mModelData.getTextures()) {
            texture.initialize(mRenderingContext);
        }
        mInitialized = true;
    }

    @Override
    public void initializeSizeDependant(int w, int h) {

    }

    @Override
    public void render() {
        if (mModelData.isVisible()) {
            bind();
            draw();
            //unbind();
        }
    }


    private void bind() {
        mModelData.getShaderProgram().bind();
        //Assign of uniforms to the shader program
        //mModelData.getShaderProgram().bindUniforms(mRenderingContext.getUniformGenerators());
        mModelData.getShaderProgram().bindUniforms(mRenderingContext.getUniformKeys());
        mModelData.getMesh().bind();
        MrTexture[] textures = mModelData.getTextures();
        for (int i = 0; i < textures.length; i++) {
            textures[i].bind();
        }
        //TODO: Quitar esto de aqui
        if (mModelData.hasSkeleton()){
            mModelData.getSkeleton().updateSkeletonPose();
        }
        mBinded = true;
    }

    private boolean isBinded() {
        return mBinded;
    }


    private void unbind() {
        MrTexture[] textures = mModelData.getTextures();
        for (int i = 0; i < textures.length; i++) {
            textures[i].unbind();
        }
        mModelData.getMesh().unbind();
        mModelData.getShaderProgram().unbind();
        mBinded = false;
    }

    private void draw() {
        mModelData.getMesh().draw();
    }
}
