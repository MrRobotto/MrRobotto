/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.scenetree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.core.controller.MrCameraController;
import mr.robotto.core.controller.MrLightController;
import mr.robotto.core.controller.MrModelController;
import mr.robotto.core.controller.MrObjectController;
import mr.robotto.core.controller.MrSceneController;
import mr.robotto.events.MrEventConstants;
import mr.robotto.renderer.MrRenderingContext;

/**
 * Created by Aarón on 18/01/2015.
 */

//TODO: SAcar el rendering context del objeto???
public class MrSceneTreeRender {
    private final ArrayList<MrUniformKey> mSortedKeys;
    private final HashMap<String, MrObjectController> mObjects;
    private MrSceneTreeData mSceneObjectsTree;
    private MrRenderingContext mContext;
    private List<MrModelController> mModels;

    public MrSceneTreeRender() {

        mSortedKeys = new ArrayList<>();
        mObjects = new HashMap<>();
    }

    public void initializeRender(MrSceneTreeData objectsTree, MrRenderingContext context) {
        mContext = context;
        mSceneObjectsTree = objectsTree;
        for (MrObjectController obj : mSceneObjectsTree) {
            obj.initializeRender(mContext);
        }
        MrRenderingSorter sorter = new MrRenderingSorter(mSceneObjectsTree.getModels());
        sorter.sort();
        mModels = sorter.getSortedModels();
    }

    public void initializeSizeDependant(int w, int h) {
        for (MrObjectController obj : mSceneObjectsTree) {
            obj.initializeSizeDependant(w, h);
        }
    }

    private void addUniforms(MrObjectController obj) {
        Map<String, MrUniformKey> keys = mContext.getUniforms();
        for (MrUniformKey key : obj.getUniformKeys().values()) {
            mSortedKeys.add(key);
            mObjects.put(key.getUniformType(), obj);
            keys.put(key.getUniformType(), key);
        }
    }

    private void updateUniforms() {
        //TODO: Use heap instead arraylist
        Collections.sort(mSortedKeys);
        Map<String, MrUniformKey> keys = mContext.getUniforms();
        for (MrUniformKey key : mSortedKeys) {
            MrObjectController obj = mObjects.get(key.getUniformType());
            obj.updateUniform(key, keys, mSceneObjectsTree.getObjectsDataTree());
        }
        mSortedKeys.clear();
    }

    //TODO: Check the visibility level
    //TODO: Solo necesitas pasar por los uniform del shader asociado al objeto si no pasas solo esos podría fallar, un modelo sin textura por ej
    private void updateUniforms(MrObjectController obj) {
        mContext.getUniforms().putAll(obj.getUniformKeys());
        for (MrUniformKey key : obj.getUniformKeys().values()) {
            obj.updateUniform(key, mContext.getUniforms(), mSceneObjectsTree.getObjectsDataTree());
        }
    }

    private void updateEvents() {
        for (MrObjectController obj : mSceneObjectsTree) {
            obj.getEventsListener().queueEvent(MrEventConstants.ON_TICK, null);
            obj.updateEvents();
        }
    }

    //TODO: This must be changed!!
    //TODO: Esta sección devora memoria como ella sola y está en el updateUniforms
    public void render() {
        mContext.getUniforms().clear();
        mObjects.clear();
        updateEvents();
        MrSceneController scene = mSceneObjectsTree.getScene();
        MrCameraController camera = mSceneObjectsTree.getActiveCamera();
        scene.render();
        camera.render();
        for (MrLightController light : mSceneObjectsTree.getLights()) {
            addUniforms(light);
        }
        mModels = mSceneObjectsTree.getModels();
        for (int i = 0; i < mModels.size(); i++) {
            MrModelController model = mModels.get(i);
            addUniforms(model);
            addUniforms(camera);
            addUniforms(scene);
            updateUniforms();
            model.render();
        }
    }

    //TODO: Fill this
    public boolean isInitialized() {
        return false;
    }

}
