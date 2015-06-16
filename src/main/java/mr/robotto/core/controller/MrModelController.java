/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import java.util.Iterator;
import java.util.Map;

import mr.robotto.commons.MrDataType;
import mr.robotto.components.comp.MrMesh;
import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.comp.MrTexture;
import mr.robotto.components.data.action.MrSkeletalAction;
import mr.robotto.components.data.material.MrMaterial;
import mr.robotto.components.data.skeleton.MrBone;
import mr.robotto.components.data.skeleton.MrSkeleton;
import mr.robotto.components.data.uniformgenerator.MrUniformGenerator;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.core.data.MrModelData;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.renderer.MrModelRender;
import mr.robotto.linearalgebra.MrLinearAlgebraObject;
import mr.robotto.linearalgebra.MrLinearAlgebraObjectContainer;
import mr.robotto.linearalgebra.MrMatrix4f;
import mr.robotto.linearalgebra.MrSamplerIndices;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector4f;
import mr.robotto.scenetree.MrObjectsDataTree;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelController extends MrObjectController {


    public MrModelController(String name, MrTransform transform, Map<String, MrUniformKey> uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials, MrSkeleton skeleton) {
        super(new MrModelData(name, transform, uniformKeys, shaderProgram, mesh, materials, skeleton), new MrModelRender());
    }

    public MrMesh getMesh() {
        return ((MrModelData) mData).getMesh();
    }

    public MrMaterial[] getMaterials() {
        return ((MrModelData) mData).getMaterials();
    }

    public boolean hasSkeleton() {
        return ((MrModelData) mData).hasSkeleton();
    }

    public MrSkeleton getSkeleton() {
        return ((MrModelData) mData).getSkeleton();
    }

    public MrTexture[] getTextures() {
        return ((MrModelData) mData).getTextures();
    }

    public Map<String, MrSkeletalAction> getSkeletalActions() {
        return ((MrModelData)mData).getSkeletalActions();
    }
}
