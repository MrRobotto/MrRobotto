/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

import mr.robotto.engine.events.MrEventDispatcher;
import mr.robotto.engine.loader.MrResources;
import mr.robotto.engine.loader.file.MrMrrLoader;
import mr.robotto.engine.renderer.MrRenderer;
import mr.robotto.engine.scenetree.MrSceneTreeController;
import mr.robotto.engine.ui.MrSurfaceView;
import mr.robotto.sceneobjects.MrObject;
import mr.robotto.scenetree.MrSceneTree;

/**
 * Main class of MrRobotto 3D Engine
 */
//TODO: Hay que tener cuidado con todos estos métodos, no sé si sin thread safe
public class MrRobottoEngine {

    protected static final MrResources sResources = new MrResources();
    protected MrSurfaceView mSurfaceView;
    protected Context mAndroidContext;
    protected MrSceneTreeController mController;
    protected MrSceneTree mSceneTree;

    /**
     * Creates a new MrRobotto Engine instance
     *
     * @param androidContext Android context attached to the engine.
     * @param surfaceView    Custom surface view used to render the scene.
     */
    public MrRobottoEngine(Context androidContext, MrSurfaceView surfaceView) {
        mAndroidContext = androidContext;
        mSurfaceView = surfaceView;
    }

    //TODO: Esto es llamado desde los loaders pero... es thread safe??

    /**
     * Gets the resources used in scene loading time. Once the scene is loaded all resourcer are released.
     * This method should not be called by the user.
     * @return
     */
    public static MrResources getResources() {
        return sResources;
    }

    /**
     * Sets the maximum FPS the engine will run
     * @param fps maximum FPS
     */
    public void setFps(int fps) {
        mSurfaceView.getRenderer().setFPS(fps);
    }

    /**
     * Gets the view where the engine is running
     * @return The attached surface view
     */
    public MrSurfaceView getSurfaceView() {
        return mSurfaceView;
    }

    /**
     * Gets the current scene
     * @return the scene, null if the scene has not been loaded
     */
    public MrSceneTree getSceneTree() {
        return mSceneTree;
    }

    /**
     * Searchs an object
     * @param name name of object
     * @return the object with the specified name, null if it does not exist
     */
    public MrObject getObject(String name) {
        return mSceneTree.findByKey(name);
    }

    /**
     * Gets the attached event dispatcher
     * @return the current event dispatcher
     */
    public MrEventDispatcher getEventDispatcher() {
        return mController.getEventDispatcher();
    }

    /**
     * Sets a new event dispatcher
     * @param eventDispatcher new event dispatcher to be used
     */
    public void setEventDispatcher(MrEventDispatcher eventDispatcher) {
        mController.setEventDispatcher(eventDispatcher);
    }

    /**
     * Loads a new scene from stream
     * @param inputStream stream containing the scene
     * @return the loaded scene for chaining
     */
    public MrSceneTree loadSceneTree(InputStream inputStream) {
        long startTime = System.currentTimeMillis();
        MrMrrLoader loader = new MrMrrLoader(inputStream);
        MrSceneTree tree = null;
        try {
            //tree = loader.parse();
            //mController = new MrSceneTreeController(tree, new MrSceneTreeRender());
            mSceneTree = loader.parse();
            initialize();
            long stopTime = System.currentTimeMillis();
            Log.v("Load Time(ms)", String.valueOf(stopTime - startTime));
            return tree;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads the scene asynchronously
     * @param inputStream stream containing the scene
     */
    public void loadSceneTreeAsync(final InputStream inputStream) {
        AsyncTask<InputStream, Void, MrSceneTree> task = new AsyncTask<InputStream, Void, MrSceneTree>() {
            @Override
            protected MrSceneTree doInBackground(InputStream... params) {
                InputStream inputStream = params[0];
                MrMrrLoader loader = new MrMrrLoader(inputStream);
                try {
                    MrSceneTree tree = loader.parse();
                    return tree;
                } catch (IOException e) {
                    cancel(true);
                    e.printStackTrace();
                } catch (JSONException e) {
                    cancel(true);
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(MrSceneTree tree) {
                super.onPostExecute(tree);
                if (tree != null) {
                    mSceneTree = tree;
                    mController = mSceneTree.getController();
                    //mController = new MrSceneTreeController(tree, new MrSceneTreeRender());
                    initialize();
                }
            }
        };
        task.execute(inputStream);
    }

    private void freeResources() {
        sResources.freeResources();
    }

    /**
     * Once the scene is loaded and initialized this method is called.
     * This method can be overridden to manage all initialization code.
     */
    public void onInitialize() {
    }

    protected final void initialize() {
        mSceneTree.setRobottoEngine(this);
        mController = mSceneTree.getController();
        mSceneTree.getController().initializeEventDispatcher(this);
        mSurfaceView.getRenderer().setController(mController);
        mSurfaceView.setOnTouchListener(mController.getEventDispatcher());
        mSurfaceView.queueEvent(new RobottoInitializationRunnable(this));
        onInitialize();
    }

    /**
     * Queues a runnable to be executed on OpenGL Thread
     * @param runnable code to be executed
     */
    public void queueEvent(Runnable runnable) {
        mSurfaceView.queueEvent(runnable);
    }

    private static class RobottoInitializationRunnable implements Runnable {

        private final MrRobottoEngine mEngine;

        public RobottoInitializationRunnable(MrRobottoEngine robottoEngine) {
            mEngine = robottoEngine;
        }

        @Override
        public void run() {
            MrRenderer renderer = mEngine.mSurfaceView.getRenderer();
            renderer.setController(mEngine.mController);
            if (renderer.isInitialized()) {
                mEngine.mController.initializeRender();
                mEngine.mController.initializeSizeDependant(mEngine.mSurfaceView.getWidth(), mEngine.mSurfaceView.getHeight());
                mEngine.freeResources();
            }
        }
    }
}
