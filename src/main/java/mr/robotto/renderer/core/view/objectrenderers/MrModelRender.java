/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.view.objectrenderers;

import mr.robotto.renderer.core.data.model.MrModelData;
import mr.robotto.renderer.core.data.object.keys.MrUniformKeyList;
import mr.robotto.renderer.core.view.core.MrDrawable;
import mr.robotto.renderer.core.view.resources.MrMeshDrawer;
import mr.robotto.renderer.core.view.resources.MrShaderProgramBinder;

public class MrModelRender implements MrObjectRender<MrModelData>, MrDrawable<MrModelData> {

    private MrMeshDrawer meshDrawer;
    private MrShaderProgramBinder shaderProgramBinder;
    private MrModelData model;
    private MrUniformKeyList uniformKeyList;
    private boolean initialized = false;
    private boolean linked = false;
    private boolean binded = false;

    public MrModelRender() {
        meshDrawer = new MrMeshDrawer();
        shaderProgramBinder = new MrShaderProgramBinder();
    }

    @Override
    public boolean isLinked() {
        return linked;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public boolean isBinded() {
        return binded;
    }

    @Override
    public void initialize() {
        meshDrawer.initialize();
        shaderProgramBinder.initialize();
        initialized = true;
    }

    @Override
    public void linkWith(MrModelData link) {
        model = link;
        meshDrawer.linkWith(model.getMesh());
        shaderProgramBinder.linkWith(model.getShaderProgram());
        linked = true;
    }

    @Override
    public void bind() {
        shaderProgramBinder.bind();
        meshDrawer.bind();
        binded = true;
    }

    @Override
    public void unbind() {
        meshDrawer.unbind();
        shaderProgramBinder.unbind();
        binded = false;
    }

    @Override
    public void setUniforms(MrUniformKeyList uniformList) {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        meshDrawer.draw();
    }

    @Override
    public void render() {
        bind();
        update();
        draw();
        unbind();
    }

}
