/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.renderer;

import android.opengl.GLES20;

import mr.robotto.engine.core.data.MrObjectData;
import mr.robotto.engine.core.data.MrSceneData;
import mr.robotto.engine.linearalgebra.MrVector4f;
import mr.robotto.engine.renderer.MrRenderingContext;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrSceneRender implements MrObjectRender {

    private MrSceneData mSceneData;
    private boolean mInitialized;

    public MrSceneRender() {
        mInitialized = false;
    }

    @Override
    public boolean isInitialized() {
        return mInitialized;
    }

    @Override
    public void initializeSizeDependant(int w, int h) {

    }

    @Override
    public void initializeRender(MrRenderingContext context, MrObjectData link) {
        mSceneData = (MrSceneData) link;

        MrVector4f clearColor = mSceneData.getClearColor();
        GLES20.glClearColor(clearColor.w, clearColor.x, clearColor.y, clearColor.z);
        GLES20.glClearDepthf(1.0f);
        //GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        //GLES20.glDepthFunc(GLES20.GL_ALWAYS);

        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        //GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        GLES20.glDisable(GLES20.GL_BLEND);

        mInitialized = true;
    }

    @Override
    public void render() {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }
}
