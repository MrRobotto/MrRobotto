/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import mr.robotto.renderer.controllers.MrModelController;
import mr.robotto.renderer.data.scene.MrSceneData;

public class MrRenderer implements GLSurfaceView.Renderer {

    private MrSceneData scene;
    private boolean initialized;

    //TODO: Remove this
    public MrModelController model;

    public MrRenderer() {
        scene = new MrSceneData("Scene");
    }

    public MrRenderer(MrSceneData scene) {
        this.scene = scene;
    }

    public void setScene(MrSceneData scene) {
        this.scene = scene;
        if (isInitialized()) {
            this.scene.initialize();
        }
    }

    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        scene.getRenderer().initialize();
        initialized = true;

        model.initialize();
    }

    //TODO: De esto se encarga la camara en teoria
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        scene.getRenderer().render();

        model.render();
    }
}
