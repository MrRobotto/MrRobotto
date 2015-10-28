/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.core.controller;

import java.util.Map;

import mr.robotto.engine.components.action.MrSkeletalAction;
import mr.robotto.engine.components.material.MrMaterial;
import mr.robotto.engine.components.material.MrTexture;
import mr.robotto.engine.components.mesh.MrMesh;
import mr.robotto.engine.components.shader.MrShaderProgram;
import mr.robotto.engine.components.skeleton.MrSkeleton;
import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.core.data.MrModelData;
import mr.robotto.engine.core.renderer.MrModelRender;
import mr.robotto.engine.linearalgebra.MrTransform;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelController extends MrObjectController {

    public MrModelController(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials, MrSkeleton skeleton) {
        super(createModel(name, transform, uniformKeys, shaderProgram, mesh, materials, skeleton), new MrModelRender());
    }

    private static MrModelData createModel(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials, MrSkeleton skeleton) {
        MrModelData.Builder builder = new MrModelData.Builder();
        builder
                .setName(name)
                .setTransform(transform)
                .setUniformKeys(uniformKeys)
                .setProgram(shaderProgram);
        builder
                .setMesh(mesh)
                .setMaterials(materials)
                .setSkeleton(skeleton);
        return builder.build();
    }

    @Override
    public MrModelData getData() {
        return (MrModelData) mData;
    }

    public MrMesh getMesh() {
        return getData().getMesh();
    }

    public boolean hasSkeleton() {
        return getData().hasSkeleton();
    }

    public MrTexture[] getTextures() {
        return getData().getTextures();
    }

    public MrSkeleton getSkeleton() {
        return getData().getSkeleton();
    }

    public MrMaterial[] getMaterials() {
        return getData().getMaterials();
    }

    public Map<String, MrSkeletalAction> getSkeletalActions() {
        return getData().getSkeletalActions();
    }

    public void playActionContinuosly(String actionName) {
        getSkeleton().playActionContinuosly(actionName);
    }

    public void playAction(String actionName) {
        getSkeleton().playAction(actionName);
    }

    public boolean isVisible() {
        return getData().isVisible();
    }

    public void setVisibility(boolean isVisible) {
        getData().setVisibility(isVisible);
    }

    public boolean hasTextures() {
        return getData().hasTextures();
    }
}
