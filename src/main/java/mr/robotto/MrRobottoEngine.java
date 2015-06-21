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

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

import mr.robotto.core.MrObject;
import mr.robotto.events.MrEventDispatcher;
import mr.robotto.loader.file.MrRobottoFileLoader;
import mr.robotto.renderer.MrRenderer;
import mr.robotto.scenetree.MrSceneTree;
import mr.robotto.scenetree.MrSceneTreeController;
import mr.robotto.ui.MrSurfaceView;

/**
 * Created by aaron on 22/04/2015.
 */
//TODO: Hay que tener cuidado con todos estos métodos, no sé si sin thread safe
public class MrRobottoEngine {

    protected static final MrResources sResources = new MrResources();
    protected MrSurfaceView mSurfaceView;
    protected Context mAndroidContext;
    protected MrSceneTreeController mController;
    protected MrSceneTree mSceneTree;

    public MrRobottoEngine(Context androidContext, MrSurfaceView surfaceView) {
        mAndroidContext = androidContext;
        mSurfaceView = surfaceView;
    }

    //TODO: Esto es llamado desde los loaders pero... es thread safe??
    public static MrResources getResources() {
        return sResources;
    }

    public void setFps(int fps) {
        mSurfaceView.getRenderer().setFPS(fps);
    }

    public MrSurfaceView getSurfaceView() {
        return mSurfaceView;
    }

    public void setSurfaceView(MrSurfaceView surfaceView) {
        mSurfaceView = surfaceView;
    }

    public void setAndroidContext(Context context) {
        mAndroidContext = context;
    }

    public MrSceneTree getSceneTree() {
        return mSceneTree;
    }

    public MrObject getObject(String name) {
        return mSceneTree.findByKey(name);
    }

    public MrEventDispatcher getEventDispatcher() {
        return mController.getEventDispatcher();
    }

    public void setEventDispatcher(MrEventDispatcher eventDispatcher) {
        mController.setEventDispatcher(eventDispatcher);
    }

    public MrSceneTree loadSceneTree(InputStream inputStream) {
        MrRobottoFileLoader loader = new MrRobottoFileLoader(inputStream);
        MrSceneTree tree = null;
        try {
            //tree = loader.parse();
            //mController = new MrSceneTreeController(tree, new MrSceneTreeRender());
            mSceneTree = loader.parse();
            initialize();
            return tree;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadSceneTreeAsync(final InputStream inputStream) {
        AsyncTask<InputStream, Void, MrSceneTree> task = new AsyncTask<InputStream, Void, MrSceneTree>() {
            @Override
            protected MrSceneTree doInBackground(InputStream... params) {
                InputStream inputStream = params[0];
                MrRobottoFileLoader loader = new MrRobottoFileLoader(inputStream);
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

    public void onInitialize() {
    }

    public final void initialize() {
        mSceneTree.setRobottoEngine(this);
        mController = mSceneTree.getController();
        mSceneTree.getController().initializeEventDispatcher(this);
        mSurfaceView.getRenderer().setController(mController);
        mSurfaceView.setOnTouchListener(mController.getEventDispatcher());
        mSurfaceView.queueEvent(new RobottoInitializationRunnable(this));
        onInitialize();
    }

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
