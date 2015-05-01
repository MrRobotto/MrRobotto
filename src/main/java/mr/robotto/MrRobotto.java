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
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

import mr.robotto.core.MrModel;
import mr.robotto.loader.MrResourceManagerLoader;
import mr.robotto.managers.MrResourceManager;
import mr.robotto.scenetree.MrSceneTree;
import mr.robotto.scenetree.MrSceneTreeController;
import mr.robotto.scenetree.MrSceneTreeRender;
import mr.robotto.ui.MrSurfaceView;
import mr.robotto.utils.MrReader;

/**
 * Created by aaron on 22/04/2015.
 */
public class MrRobotto {
    private static MrRobotto sEngine = new MrRobotto();

    private MrSurfaceView mSurfaceView;
    private Context mContext;
    private MrSceneTreeController mController;

    private MrRobotto() {

    }

    public static MrRobotto getInstance() {
        return sEngine;
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

    //TODO: Devolver un asynctask
    public void loadScene(String filename) {
        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(filename);
            JSONTokener tokener = new JSONTokener(MrReader.read(stream));
            JSONObject jsonObject = (JSONObject) tokener.nextValue();

            MrResourceManagerLoader loader = new MrResourceManagerLoader(jsonObject);
            MrResourceManager resources = loader.parse();
            MrResourceManager.Builder builder = new MrResourceManager.Builder(resources);
            MrSceneTree tree = builder.buildSceneObjectsTree();
            mController = new MrSceneTreeController(tree, new MrSceneTreeRender());
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
                    MrResourceManagerLoader loader = new MrResourceManagerLoader(jsonObject);
                    MrResourceManager resources = loader.parse();
                    MrResourceManager.Builder builder = new MrResourceManager.Builder(resources);
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
                mController = new MrSceneTreeController(tree, new MrSceneTreeRender());
                initialize();
            }
        };
        task.execute(jsonObject);
    }

    //TODO: Agregar como argumento un assetmanager
    public void loadSceneTree(String filename) {
        AsyncTask<String, Void, MrSceneTree> task = new AsyncTask<String, Void, MrSceneTree>() {
            @Override
            protected MrSceneTree doInBackground(String... params) {
                AssetManager am = mContext.getAssets();
                try {
                    String filename = params[0];
                    InputStream stream = am.open(filename);
                    JSONTokener tokener = new JSONTokener(MrReader.read(stream));
                    JSONObject jsonObject = (JSONObject) tokener.nextValue();

                    MrResourceManagerLoader loader = new MrResourceManagerLoader(jsonObject);
                    MrResourceManager resources = loader.parse();
                    MrResourceManager.Builder builder = new MrResourceManager.Builder(resources);
                    MrSceneTree tree = builder.buildSceneObjectsTree();
                    return tree;
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(MrSceneTree tree) {
                super.onPostExecute(tree);
                mController = new MrSceneTreeController(tree, new MrSceneTreeRender());
                initialize();
            }
        };
        task.execute(filename);
    }

    public void initialize() {
        mSurfaceView.queueEvent(new Runnable() {
            @Override
            public void run() {
                mSurfaceView.getRenderer().setController(mController);
                if (mSurfaceView.getRenderer().isInitialized()) {
                    mController.initializeRender();
                    mController.initializeSizeDependant(mSurfaceView.getWidth(), mSurfaceView.getHeight());
                    MrModel m = (MrModel)mController.getSceneTree().findByKey("Cube");
                    m.playActionContinuosly("Attack");
                }
            }
        });
    }
}
