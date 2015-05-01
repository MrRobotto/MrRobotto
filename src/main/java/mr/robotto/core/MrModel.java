/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core;

import java.util.Map;

import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.action.MrSkeletalAction;
import mr.robotto.components.data.bone.MrSkeleton;
import mr.robotto.components.data.material.MrMaterial;
import mr.robotto.components.data.material.MrMaterialMap;
import mr.robotto.components.comp.MrMesh;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.controller.MrModelController;
import mr.robotto.core.data.MrModelData;
import mr.robotto.core.renderer.MrModelRender;
import mr.robotto.linearalgebra.MrTransform;

public class MrModel extends MrObject {

    public MrModel(String name, MrTransform transform, MrUniformKeyMap uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials) {
        super(new MrModelController(name, transform, uniformKeys, shaderProgram, mesh, materials));
    }

    public MrModel(String name, MrTransform transform, MrUniformKeyMap uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials, MrSkeleton skeleton) {
        super(new MrModelController(name, transform, uniformKeys, shaderProgram, mesh, materials, skeleton));
    }


    @Override
    public MrModelController getController() {
        return (MrModelController) super.getController();
    }

    public MrMaterial[] getMaterials() {
        return getController().getMaterials();
    }

    public MrMesh getMesh() {
        return getController().getMesh();
    }

    public MrSkeleton getSkeleton() {
        return getController().getSkeleton();
    }

    public void playAction(String actionName) {
        getSkeleton().playAction(actionName);
    }

    public void playActionContinuosly(String actionName) {
        getSkeleton().playActionContinuosly(actionName);
    }

    public Map<String, MrSkeletalAction> getActions() {
        return getSkeleton().getActions();
    }
}
