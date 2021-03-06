/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import mr.robotto.engine.scenetree.MrSceneTreeController;

public class MrRenderer implements GLSurfaceView.Renderer {

    private static int sTimeRate;
    private int mFPS = 60;
    private MrSceneTreeController mController;
    private boolean mInitialized;
    private long mEndTime;
    private long mStartTime;
    private FPSCounter mFPSCounter;

    public MrRenderer() {
        mFPSCounter = new FPSCounter();
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

    public int getFPS() {
        return mFPS;
    }

    public void setFPS(int FPS) {
        mFPS = FPS;
    }

    //TODO: En vez de mirar si es null establecer una escena vacia
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        mInitialized = true;
        sTimeRate = (int) Math.floor(1.0f / mFPS * 1000);
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

    public void onDrawFrame(GL10 gl10) {
        //TODO: Check this!
        if (mController != null) {
            mEndTime = System.currentTimeMillis();
            long dt = mEndTime - mStartTime;
            if (dt < mFPS && sTimeRate - dt > 0) {
                try {
                    Thread.sleep(sTimeRate - dt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mStartTime = System.currentTimeMillis();
            mController.render();
            mFPSCounter.logFrame();
        }
    }

    public void onDrawFrame2(GL10 gl10) {
        //TODO: Check this!
        if (mController != null) {
            mController.render();
            mFPSCounter.logFrame();
            System.gc();
        }
    }

    public void onDrawFrame3(GL10 gl10) {
        //TODO: Check this!
        if (mController != null) {
            mStartTime = System.currentTimeMillis();
            mController.render();
            mEndTime = System.currentTimeMillis();
            long dt = mEndTime - mStartTime;
            if (dt < mFPS) {
                try {
                    if (sTimeRate - dt > 0) {
                        Thread.sleep(sTimeRate - dt);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mFPSCounter.logFrame();
            System.gc();
        }
    }

    private class FPSCounter {
        long startTime = System.nanoTime();
        int frames = 0;

        public void logFrame() {
            frames++;
            if (System.nanoTime() - startTime >= 1000000000) {
                Log.d("FPSCounter", "fps: " + frames);
                frames = 0;
                startTime = System.nanoTime();
            }
        }
    }


}
