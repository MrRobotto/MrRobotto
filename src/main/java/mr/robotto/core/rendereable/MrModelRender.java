/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.rendereable;

import mr.robotto.core.data.MrModelData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.data.resources.shaders.input.attributes.MrAttribute;
import mr.robotto.core.data.resources.uniformkeys.MrUniformKeyContainer;
import mr.robotto.core.rendereable.resources.MrMeshDrawer;
import mr.robotto.core.rendereable.resources.MrShaderProgramBinder;
import mr.robotto.proposed.MrRenderingContext;

public class MrModelRender implements MrObjectRender {

    private MrMeshDrawer mMeshDrawer;
    private MrShaderProgramBinder mShaderProgramBinder;
    private MrModelData mModelData;
    private MrUniformKeyContainer mUniformKeyList;
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
        mModelData = (MrModelData) link;
        mMeshDrawer.linkWith(mModelData.getMesh());
        mShaderProgramBinder.linkWith(mModelData.getShaderProgram());
        for (MrAttribute attribute : mModelData.getShaderProgram().getAttributes()) {
            mModelData.getMesh().getKeys().findByKey(attribute.getAttributeType()).setIndex(attribute.getIndex());
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
