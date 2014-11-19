/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.rendereable.objectrenderers;

import mr.robotto.renderer.core.data.model.MrModelData;
import mr.robotto.renderer.core.data.object.MrObjectData;
import mr.robotto.renderer.core.data.object.keys.MrUniformKeyList;
import mr.robotto.renderer.core.rendereable.core.MrDrawable;
import mr.robotto.renderer.core.rendereable.resources.MrMeshDrawer;
import mr.robotto.renderer.core.rendereable.resources.MrShaderProgramBinder;

public class MrModelRender implements MrObjectRender, MrDrawable {

    private MrMeshDrawer mMeshDrawer;
    private MrShaderProgramBinder mShaderProgramBinder;
    private MrModelData mModelData;
    private MrUniformKeyList mUniformKeyList;
    private boolean mInitialized = false;
    private boolean mLinked = false;
    private boolean mBinded = false;

    public MrModelRender() {
        mMeshDrawer = new MrMeshDrawer();
        mShaderProgramBinder = new MrShaderProgramBinder();
    }

    @Override
    public boolean isLinked() {
        return mLinked;
    }

    @Override
    public boolean isInitialized() {
        return mInitialized;
    }

    @Override
    public boolean isBinded() {
        return mBinded;
    }

    @Override
    public void initialize() {
        mMeshDrawer.initialize();
        mShaderProgramBinder.initialize();
        mInitialized = true;
    }

    @Override
    public void linkWith(MrObjectData link) {
        mModelData = (MrModelData) link;
        mMeshDrawer.linkWith(mModelData.getMesh());
        mShaderProgramBinder.linkWith(mModelData.getShaderProgram());
        mLinked = true;
    }

    @Override
    public void bind() {
        mShaderProgramBinder.bind();
        mMeshDrawer.bind();
        mBinded = true;
    }

    @Override
    public void unbind() {
        mMeshDrawer.unbind();
        mShaderProgramBinder.unbind();
        mBinded = false;
    }

    @Override
    public void setUniforms(MrUniformKeyList uniformList) {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        mMeshDrawer.draw();
    }

    @Override
    public void render() {
        bind();
        update();
        draw();
        unbind();
    }

}
