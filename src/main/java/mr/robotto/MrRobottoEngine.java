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
import android.graphics.Bitmap;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

import mr.robotto.core.MrObject;
import mr.robotto.events.MrEventDispatcher;
import mr.robotto.loader.file.MrRobottoFileLoader;
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
            mSceneTree.setRobottoEngine(this);
            mController = mSceneTree.getController();
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
        for (Bitmap bitmap : sResources.getTextureBitmaps().values()) {
            bitmap.recycle();
        }
    }

    public void onPreInitialize() {

    }

    public void onPostInitialize() {

    }

    public final void initialize() {
        mController.initializeEventDispatcher(this);
        mSurfaceView.setOnTouchListener(mController.getEventDispatcher());
        onPreInitialize();
        mSurfaceView.queueEvent(new Runnable() {
            @Override
            public void run() {
                mSurfaceView.getRenderer().setController(mController);
                if (mSurfaceView.getRenderer().isInitialized()) {
                    mController.initializeRender();
                    mController.initializeSizeDependant(mSurfaceView.getWidth(), mSurfaceView.getHeight());
                    freeResources();
                    onPostInitialize();
                }
            }
        });
    }

    public void queueEvent(Runnable runnable) {
        mSurfaceView.queueEvent(runnable);
    }
}
