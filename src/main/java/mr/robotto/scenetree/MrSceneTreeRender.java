/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.scenetree;

import java.util.Collection;
import java.util.List;

import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.core.controller.MrCameraController;
import mr.robotto.core.controller.MrModelController;
import mr.robotto.core.controller.MrObjectController;
import mr.robotto.core.controller.MrSceneController;
import mr.robotto.renderer.MrRenderingContext;

/**
 * Created by Aarón on 18/01/2015.
 */

//TODO: SAcar el rendering context del objeto???
public class MrSceneTreeRender {
    private MrSceneTreeData mSceneObjectsTree;
    private MrRenderingContext mContext;

    public MrSceneTreeRender() {

    }

    public void initializeRender(MrSceneTreeData objectsTree, MrRenderingContext context) {
        mContext = context;
        mSceneObjectsTree = objectsTree;
        for (MrObjectController obj : mSceneObjectsTree) {
            obj.initializeRender(mContext);
        }
    }

    public void initializeSizeDependant(int w, int h) {
        for (MrObjectController obj : mSceneObjectsTree) {
            obj.initializeSizeDependant(w, h);
        }
    }

    //TODO: Check the visibility level
    //TODO: Solo necesitas pasar por los uniform del shader asociado al objeto si no pasas solo esos podría fallar, un modelo sin textura por ej
    private void updateUniforms(MrObjectController obj) {
        mContext.getUniforms().putAll(obj.getUniformKeys());
        for (MrUniformKey key : obj.getUniformKeys().values()) {
            obj.updateUniform(key, mContext.getUniforms(), mSceneObjectsTree.getObjectsDataTree());
        }
    }

    //TODO: This must be changed!!
    //TODO: Esta sección devora memoria como ella sola y está en el updateUniforms
    public void render() {
        mContext.getUniforms().clear();
        MrSceneController scene = mSceneObjectsTree.getScene();
        MrCameraController camera = mSceneObjectsTree.getActiveCamera();
        scene.render();
        camera.render();
        List<MrModelController> models = mSceneObjectsTree.getModels();
        for (int i = 0; i < models.size(); i++) {
            MrModelController model = models.get(i);
            updateUniforms(model);
            updateUniforms(camera);
            updateUniforms(scene);
            model.render();
        }
    }

    //TODO: Fill this
    public boolean isInitialized() {
        return false;
    }
}
