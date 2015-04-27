/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import mr.robotto.scenetree.MrSceneTreeController;

public class MrRenderer implements GLSurfaceView.Renderer {

    public static final int FPS = 24;

    private MrSceneTreeController mController;
    private boolean mInitialized;
    private long mEndTime;
    private long mStartTime;

    public MrRenderer() {

    }

    public MrSceneTreeController getController() {
        return mController;
    }

    public void setController(MrSceneTreeController controller) {
        mController = controller;
    }

    public boolean isInitialized() {
        return mInitialized;
    }

    //TODO: En vez de mirar si es null establecer una escena vacia
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        mInitialized = true;
        if (mController != null)
            mController.initializeRender();
    }

    //TODO: De esto se encarga la camara en teoria
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        if (mController != null)
            mController.initializeSizeDependant(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        //TODO: Check this!
        if (mController != null) {
            mEndTime = System.currentTimeMillis();
            long dt = mEndTime - mStartTime;
            if (dt < FPS) {
                try {
                    Thread.sleep(FPS - dt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mStartTime = System.currentTimeMillis();
            mController.render();
        }
    }

    /*@Override
    public void onDrawFrame(GL10 gl10) {
        //TODO: Check this!
        if (mController != null) {
            mStartTime = System.currentTimeMillis();
            mController.render();
            mEndTime = System.currentTimeMillis();
            long dt = mEndTime - mStartTime;
            if (dt < FPS) {
                try {
                    Thread.sleep(FPS - dt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/


}
