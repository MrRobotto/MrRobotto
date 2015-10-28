/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.scenetree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.controller.MrCameraController;
import mr.robotto.engine.core.controller.MrLightController;
import mr.robotto.engine.core.controller.MrModelController;
import mr.robotto.engine.core.controller.MrObjectController;
import mr.robotto.engine.core.controller.MrSceneController;
import mr.robotto.engine.events.MrEventConstants;
import mr.robotto.engine.renderer.MrRenderingContext;
import mr.robotto.engine.renderer.MrRenderingSorter;

/**
 * Created by Aarón on 18/01/2015.
 */

public class MrSceneTreeRender {
    private final ArrayList<MrUniformKey> mSortedKeys;
    private final HashMap<String, MrObjectController> mObjects;
    private MrSceneTreeData mSceneObjectsTree;
    private MrRenderingContext mContext;
    private MrModelController[] mModels;
    private MrLightController[] mLights;
    private boolean mInitialized;

    public MrSceneTreeRender() {
        mSortedKeys = new ArrayList<>();
        mObjects = new HashMap<>();
        mInitialized = false;
    }

    public void initializeRender(MrSceneTreeData objectsTree, MrRenderingContext context) {
        mContext = context;
        mSceneObjectsTree = objectsTree;
        for (MrObjectController obj : mSceneObjectsTree) {
            obj.initializeRender(mContext);
        }
        MrRenderingSorter sorter = new MrRenderingSorter(mSceneObjectsTree.getModels());
        sorter.sort();
        {
            List<MrModelController> models = sorter.getSortedModels();
            mModels = new MrModelController[models.size()];
            models.toArray(mModels);
        }
        {
            List<MrLightController> lights = mSceneObjectsTree.getLights();
            mLights = new MrLightController[lights.size()];
            lights.toArray(mLights);
        }
        mInitialized = true;
    }

    public void initializeSizeDependant(int w, int h) {
        for (MrObjectController obj : mSceneObjectsTree) {
            obj.initializeSizeDependant(w, h);
        }
    }

    private void addUniformKeys(MrObjectController obj) {
        Map<String, MrUniformKey> keys = mContext.getUniformKeys();
        for (MrUniformKey key : obj.getUniformKeys().values()) {
            mSortedKeys.add(key);
            mObjects.put(key.getUniformType(), obj);
            keys.put(key.getUniformType(), key);
        }
    }

    private void updateUniformKeys() {
        Collections.sort(mSortedKeys);
        Map<String, MrUniformKey> keys = mContext.getUniformKeys();
        for (MrUniformKey key : mSortedKeys) {
            MrObjectController obj = mObjects.get(key.getUniformType());
            obj.updateUniform(key, keys, mSceneObjectsTree.getObjectsDataTree());
        }
        mSortedKeys.clear();
    }

    private void updateEvents() {
        for (MrObjectController obj : mSceneObjectsTree) {
            obj.getEventsListener().queueEvent(MrEventConstants.ON_TICK, null);
            obj.updateEvents();
        }
    }

    public void render() {
        long start = System.nanoTime();
        mContext.getUniformKeys().clear();
        mObjects.clear();
        updateEvents();
        MrSceneController scene = mSceneObjectsTree.getScene();
        MrCameraController camera = mSceneObjectsTree.getActiveCamera();
        scene.render();
        camera.render();
        for (int i = 0; i < mLights.length; i++) {
            addUniformKeys(mLights[i]);
        }
        for (int i = 0; i < mModels.length; i++) {
            MrModelController model = mModels[i];
            if (model.isVisible()) {
                //long start = System.nanoTime();
                addUniformKeys(model);
                addUniformKeys(camera);
                addUniformKeys(scene);
                updateUniformKeys();
                //long stop = System.nanoTime();
                //System.out.println("Tardo1: "+(stop-start));
                //start = System.nanoTime();
                model.render();
                //stop = System.nanoTime();
                //System.out.println("Tardo2: "+(stop-start));
            }
        }
        long stop = System.nanoTime();
        //System.out.println("Tardo: "+(stop-start)/1000000.0);
    }

    //TODO: Fill this
    public boolean isInitialized() {
        return false;
    }

}
