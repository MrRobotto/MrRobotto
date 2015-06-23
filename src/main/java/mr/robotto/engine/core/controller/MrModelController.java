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

import mr.robotto.engine.components.comp.MrMesh;
import mr.robotto.engine.components.comp.MrShaderProgram;
import mr.robotto.engine.components.comp.MrTexture;
import mr.robotto.engine.components.data.action.MrSkeletalAction;
import mr.robotto.engine.components.data.material.MrMaterial;
import mr.robotto.engine.components.data.skeleton.MrSkeleton;
import mr.robotto.engine.components.data.uniformkey.MrUniformKey;
import mr.robotto.engine.core.data.MrModelData;
import mr.robotto.engine.core.renderer.MrModelRender;
import mr.robotto.engine.linearalgebra.MrTransform;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelController extends MrObjectController {


    public MrModelController(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials, MrSkeleton skeleton) {
        super(new MrModelData(name, transform, uniformKeys, shaderProgram, mesh, materials, skeleton), new MrModelRender());
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
