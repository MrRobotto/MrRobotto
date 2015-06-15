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
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

import mr.robotto.core.MrObject;
import mr.robotto.loader.MrRobottoFileLoader;
import mr.robotto.loader.MrRobottoJson;
import mr.robotto.loader.MrRobottoJsonLoader;
import mr.robotto.scenetree.MrSceneTree;
import mr.robotto.scenetree.MrSceneTreeController;
import mr.robotto.ui.MrSurfaceView;
import mr.robotto.utils.MrStreamReader;

/**
 * Created by aaron on 22/04/2015.
 */
//TODO: Hay que tener cuidado con todos estos métodos, no sé si sin thread safe
public class MrRobotto {
    private static MrRobotto sEngine = new MrRobotto();
    private static MrResources sResources = new MrResources();

    private MrSurfaceView mSurfaceView;
    private Context mContext;
    private MrSceneTreeController mController;
    private MrSceneTree mSceneTree;

    private MrRobotto() {

    }

    public static MrRobotto getInstance() {
        return sEngine;
    }

    //TODO: Esto es llamado desde los loaders pero... es thread safe??
    public static MrResources getsResources() {
        return sResources;
    }

    public MrSurfaceView getSurfaceView() {
        return mSurfaceView;
    }

    public void setSurfaceView(MrSurfaceView surfaceView) {
        mSurfaceView = surfaceView;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public MrSceneTree getSceneTree() {
        return mSceneTree;
    }

    public MrObject getObject(String name) {
        return mSceneTree.findByKey(name);
    }

    //TODO: Devolver un asynctask
    public void loadScene(String filename) {
        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(filename);
            JSONTokener tokener = new JSONTokener(MrStreamReader.read(stream));
            JSONObject jsonObject = (JSONObject) tokener.nextValue();

            MrRobottoJsonLoader loader = new MrRobottoJsonLoader(jsonObject);
            MrRobottoJson resources = loader.parse();
            MrRobottoJson.Builder builder = new MrRobottoJson.Builder(resources);
            //MrSceneTree tree = builder.buildSceneObjectsTree();
            //mController = new MrSceneTreeController(tree, new MrSceneTreeRender());
            mSceneTree = builder.buildSceneObjectsTree();
            mController = mSceneTree.getController();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void loadSceneTree(JSONObject jsonObject) {
        AsyncTask<JSONObject, Void, MrSceneTree> task = new AsyncTask<JSONObject, Void, MrSceneTree>() {
            @Override
            protected MrSceneTree doInBackground(JSONObject... params) {
                JSONObject jsonObject = params[0];
                try {
                    MrRobottoJsonLoader loader = new MrRobottoJsonLoader(jsonObject);
                    MrRobottoJson resources = loader.parse();
                    MrRobottoJson.Builder builder = new MrRobottoJson.Builder(resources);
                    MrSceneTree tree = builder.buildSceneObjectsTree();
                    return tree;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(MrSceneTree tree) {
                super.onPostExecute(tree);
                //mController = new MrSceneTreeController(tree, new MrSceneTreeRender());
                mSceneTree = tree;
                mController = mSceneTree.getController();
                initialize();
            }
        };
        task.execute(jsonObject);
    }

    public void loadSceneTree(InputStream inputStream) {
        MrRobottoFileLoader loader = new MrRobottoFileLoader(inputStream);
        MrSceneTree tree = null;
        try {
            //tree = loader.parse();
            //mController = new MrSceneTreeController(tree, new MrSceneTreeRender());
            mSceneTree = loader.parse();
            mController = mSceneTree.getController();
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public void initialize() {
        mSurfaceView.queueEvent(new Runnable() {
            @Override
            public void run() {
                mSurfaceView.getRenderer().setController(mController);
                if (mSurfaceView.getRenderer().isInitialized()) {
                    mController.initializeRender();
                    mController.initializeSizeDependant(mSurfaceView.getWidth(), mSurfaceView.getHeight());
                    freeResources();
                }
            }
        });
    }

    public void queueEvent(Runnable runnable) {
        mSurfaceView.queueEvent(runnable);
    }
}
