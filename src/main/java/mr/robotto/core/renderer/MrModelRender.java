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
import mr.robotto.components.renderer.MrMeshDrawer;
import mr.robotto.components.renderer.MrShaderProgramBinder;
import mr.robotto.core.data.MrModelData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.renderer.MrRenderingContext;

public class MrModelRender implements MrObjectRender {

    private MrMeshDrawer mMeshDrawer;
    private MrShaderProgramBinder mShaderProgramBinder;
    private MrModelData mModelData;
    private MrRenderingContext mContext;
    private boolean mInitialized = false;
    private boolean mBinded = false;

    public MrModelRender() {
        mMeshDrawer = new MrMeshDrawer();
        mShaderProgramBinder = new MrShaderProgramBinder();
    }

    @Override
    public boolean isInitialized() {
        return mInitialized;
    }

    @Override
    public void initializeRender(MrObjectData link, MrRenderingContext context) {
        mContext = context;
        mModelData = (MrModelData) link;
        mMeshDrawer.linkWith(mModelData.getMesh());
        mShaderProgramBinder.linkWith(mModelData.getShaderProgram());
        //Attribute index assignation to mesh
        MrBufferKeyMap keyMap = mModelData.getMesh().getBufferKeys();
        for (MrAttribute attribute : mModelData.getShaderProgram().getAttributes()) {
            MrBufferKey key = keyMap.findByKey(attribute.getAttributeType());
            key.setIndex(attribute.getIndex());
        }
        mMeshDrawer.initialize();
        mShaderProgramBinder.initialize();
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
        mShaderProgramBinder.bind();
        //Assign of uniforms to the shader program
        mShaderProgramBinder.bindUniforms(mContext.getUniformGenerators());
        mMeshDrawer.bind();
        mBinded = true;
    }

    private boolean isBinded() {
        return mBinded;
    }


    private void unbind() {
        mMeshDrawer.unbind();
        mShaderProgramBinder.unbind();
        mBinded = false;
    }

    private void draw() {
        mMeshDrawer.draw();
    }

}
