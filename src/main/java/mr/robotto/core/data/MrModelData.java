/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data;

import mr.robotto.components.data.mesh.MrMesh;
import mr.robotto.components.data.shader.MrShaderProgram;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.linearalgebra.MrTransform;

public class MrModelData extends MrObjectData {
    private MrMesh mMesh;
    private MrShaderProgram mShaderProgram;

    public MrModelData(String name, MrTransform transform, MrUniformKeyMap uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh) {
        super(name, MrSceneObjectType.MODEL, transform, uniformKeys);
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
