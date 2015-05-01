/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data;

import mr.robotto.components.comp.MrMesh;
import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.bone.MrSkeleton;
import mr.robotto.components.data.material.MrMaterial;
import mr.robotto.components.data.material.MrMaterialMap;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.linearalgebra.MrTransform;

/**
 * Created by aaron on 14/04/2015.
 */
public class MrModelData extends MrObjectData {
    private MrMesh mMesh;
    private MrMaterial[] mMaterials;
    private MrSkeleton mSkeleton;

    public MrModelData(String name, MrTransform transform, MrUniformKeyMap uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials) {
        super(name, MrSceneObjectType.MODEL, transform, shaderProgram, uniformKeys);
        mMesh = mesh;
        mMaterials = materials;
        mSkeleton = null;
    }

    public MrModelData(String name, MrTransform transform, MrUniformKeyMap uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh, MrMaterial[] materials, MrSkeleton skeleton) {
        super(name, MrSceneObjectType.MODEL, transform, shaderProgram, uniformKeys);
        mMesh = mesh;
        mMaterials = materials;
        mSkeleton = skeleton;
    }

    public MrMesh getMesh() {
        return mMesh;
    }

    public MrMaterial[] getMaterials() {
        return mMaterials;
    }

    public MrSkeleton getSkeleton() {
        return mSkeleton;
    }
}
