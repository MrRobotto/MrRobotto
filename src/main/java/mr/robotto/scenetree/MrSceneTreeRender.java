/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.scenetree;

import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.MrObject;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.renderer.MrRenderingContext;

/**
 * Created by Aarón on 18/01/2015.
 */

//TODO: SAcar el rendering context del objeto???
public class MrSceneTreeRender {
    private MrSceneTree mSceneObjectsTree;
    private MrUniformKeyMap mUniforms;
    private MrRenderingContext mContext;

    public MrSceneTreeRender() {

    }

    public void initializeRender(MrSceneTree objectsTree, MrRenderingContext context) {
        mContext = context;
        mUniforms = mContext.getUniforms();
        mSceneObjectsTree = objectsTree;
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
    //TODO: Solo necesitas pasar por los uniform del shader asociado al objeto si no pasas solo esos podría fallar, un modelo sin textura por ej
    private void updateUniforms(MrObject obj) {
        /*MrShaderProgram program = obj.getShaderProgram();
        if (program == null) {
            return;
        }
        mUniforms.addAll(obj.getUniformKeys());
        for (MrUniform uniform : program.getUniforms()) {
            MrUniformKey key = obj.getUniformKeys().findByKey(uniform.getUniformType());
            if (key != null) {
                mUniforms.setVisibility(key.getLevel());
                obj.updateUniform(key, mContext.getUniforms().getView(), mSceneObjectsTree);

            }
        }*/

        mContext.getUniforms().addAll(obj.getUniformKeys());
        for (MrUniformKey key : obj.getUniformKeys()) {
            mContext.getUniforms().setVisibility(key.getLevel());
            obj.updateUniform(key, mContext.getUniforms().getView(), mSceneObjectsTree);
        }
    }

    //TODO: This must be changed!!
    //TODO: Esta sección devora memoria como ella sola y está en el updateUniforms
    public void render() {
        for (MrObject scene : mSceneObjectsTree.getByType(MrSceneObjectType.SCENE)) {
            //updateUniforms(scene);
            scene.render();
            for (MrObject camera : mSceneObjectsTree.getByType(MrSceneObjectType.CAMERA)) {
                camera.render();
                for (MrObject model : mSceneObjectsTree.getByType(MrSceneObjectType.MODEL)) {
                    updateUniforms(model);
                    updateUniforms(camera);
                    updateUniforms(scene);
                    model.render();
                }
            }
        }
    }

    //TODO: Fill this
    public boolean isInitialized() {
        return false;
    }
}
