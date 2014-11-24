/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data;

import mr.robotto.core.data.resources.MrTransform;
import mr.robotto.core.data.resources.commons.MrSceneObjType;
import mr.robotto.core.data.resources.mesh.MrMesh;
import mr.robotto.core.data.resources.shaders.MrShaderProgram;
import mr.robotto.core.data.resources.uniformkeys.MrUniformKeyContainer;

public class MrModelData extends MrObjectData
{
    private MrMesh mMesh;
    private MrShaderProgram mShaderProgram;

    public MrModelData(String name, MrTransform transform, MrUniformKeyContainer uniformKeys, MrShaderProgram shaderProgram,  MrMesh mesh) {
        super(name, MrSceneObjType.MODEL, transform, uniformKeys);
        mMesh = mesh;
        mShaderProgram = shaderProgram;
    }

    public MrMesh getMesh() {
        return mMesh;
    }

    public MrShaderProgram getShaderProgram() {
        return mShaderProgram;
    }
}
