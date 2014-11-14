/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.view.objectrenderers;

import android.opengl.GLES20;

import mr.robotto.renderer.core.data.scene.MrSceneData;
import mr.robotto.renderer.linearalgebra.MrVector4f;
import mr.robotto.renderer.core.data.object.keys.MrUniformKeyList;

public class MrSceneRender implements MrObjectRender<MrSceneData> {

    private MrSceneData scene;
    private boolean linked;
    private boolean initialized;

    public MrSceneRender() {
        linked = false;
        initialized = false;
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
    public void linkWith(MrSceneData link) {
        scene = link;
        linked = true;
    }

    @Override
    public void initialize() {
        MrVector4f clearColor = scene.getClearColor();
        GLES20.glClearColor(clearColor.w, clearColor.x, clearColor.y, clearColor.z);
        GLES20.glClearDepthf(1.0f);
        //GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        //GLES20.glDepthFunc(GLES20.GL_ALWAYS);

        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        //GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        GLES20.glDisable(GLES20.GL_BLEND);

        initialized = true;
    }

    @Override
    public void setUniforms(MrUniformKeyList uniformList) {

    }

    @Override
    public void render() {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }
}
