/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.context;

import java.util.Stack;

import mr.robotto.core.controller.MrObject;
import mr.robotto.core.controller.uniformgenerator.MrUniformGenerator;
import mr.robotto.core.data.MrSceneObjectType;
import mr.robotto.proposed.MrRenderingContext;

/**
 * Created by Aarón on 18/01/2015.
 */

//TODO: SAcar el rendering context del objeto???
public class MrSceneObjectsTreeRender {
    private MrSceneObjectsTree mSceneObjectsTree;
    private MrRenderingContext mContext;
    private Stack<MrObject> mActiveObjects;

    public MrSceneObjectsTreeRender() {
        mActiveObjects = new Stack<>();
    }

    public void initializeRender(MrSceneObjectsTree objectsTree, MrRenderingContext context) {
        mContext = context;
        mSceneObjectsTree = objectsTree;
        for (MrObject obj : mSceneObjectsTree) {
            obj.initializeRender(context);
        }
    }

    public void initializeSizeDependant(int w, int h) {
        for (MrObject obj : mSceneObjectsTree) {
            obj.initializeSizeDependant(w, h);
        }
    }

    //TODO: CHANGEEEEE
    private void updateUniforms() {
        for (MrObject obj : mSceneObjectsTree) {
            for (MrUniformGenerator generator : obj.getUniformGenerators()) {
                generator.setUniformValue(generator.generateUniform(mSceneObjectsTree, obj));
            }
            mContext.getUniformGenerators().addAll(obj.getUniformGenerators());
        }
    }

    private void updateUniforms(MrObject obj) {
        for (MrUniformGenerator generator : obj.getUniformGenerators()) {
            generator.setUniformValue(generator.generateUniform(mSceneObjectsTree, obj));
        }
        mContext.getUniformGenerators().addAll(obj.getUniformGenerators());
    }

    /*private void renderActiveObjects() {
        for (MrObject object : mActiveObjects) {

        }
    }*/

    //TODO: This must be changed!!
    public void render() {
        for (MrObject scene : mSceneObjectsTree.getByType(MrSceneObjectType.SCENE)) {
            updateUniforms(scene);
            scene.render();
            for (MrObject model : mSceneObjectsTree.getByType(MrSceneObjectType.MODEL)) {
                updateUniforms(model);
                model.render();
            }
        }
    }

    //TODO: Fill this
    public boolean isInitialized() {
        return false;
    }
}
