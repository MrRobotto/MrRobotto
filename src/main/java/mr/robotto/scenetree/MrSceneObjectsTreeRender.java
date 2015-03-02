/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.scenetree;

import java.util.Stack;

import mr.robotto.core.MrSceneObjectType;
import mr.robotto.core.controller.MrObject;
import mr.robotto.renderer.MrRenderingContext;
import mr.robotto.renderer.uniformgenerator.MrUniformGenerator;
import mr.robotto.renderer.uniformgenerator.MrUniformGeneratorMapController;

/**
 * Created by Aarón on 18/01/2015.
 */

//TODO: SAcar el rendering context del objeto???
public class MrSceneObjectsTreeRender {
    private MrSceneObjectsTree mSceneObjectsTree;
    private MrRenderingContext mContext;
    private MrUniformGeneratorMapController mUniformController;
    private Stack<MrObject> mActiveObjects;

    public MrSceneObjectsTreeRender() {
        mActiveObjects = new Stack<>();
    }

    public void initializeRender(MrSceneObjectsTree objectsTree, MrRenderingContext context) {
        mContext = context;
        mSceneObjectsTree = objectsTree;
        mUniformController = new MrUniformGeneratorMapController(mContext.getUniformGenerators());
        for (MrObject obj : mSceneObjectsTree) {
            obj.initializeRender(mContext);
        }
    }

    public void initializeSizeDependant(int w, int h) {
        for (MrObject obj : mSceneObjectsTree) {
            obj.initializeSizeDependant(w, h);
        }
    }

    //TODO: Check the visibility level
    //TODO: Solo necesitas pasar por los uniform del shader asociado al objeto
    private void updateUniforms(MrObject obj) {
        mUniformController.getUniformGenerators().addAll(obj.getUniformGenerators());
        for (MrUniformGenerator generator : obj.getUniformGenerators()) {
            mUniformController.setVisibility(generator.getPriority());
            generator.updateUniform(mSceneObjectsTree, mUniformController.getView(), obj);
        }
    }

    //TODO: This must be changed!!
    //TODO: Esta sección devora memoria como ella sola y está en el updateUniforms
    public void render() {
        for (MrObject scene : mSceneObjectsTree.getByType(MrSceneObjectType.SCENE)) {
            //updateUniforms(scene);
            scene.render();
            for (MrObject model : mSceneObjectsTree.getByType(MrSceneObjectType.MODEL)) {
                updateUniforms(model);
                updateUniforms(scene);
                model.render();
            }
        }
    }

    //TODO: Fill this
    public boolean isInitialized() {
        return false;
    }
}
