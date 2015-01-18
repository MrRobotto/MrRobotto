/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.data.model;

import mr.robotto.core.MrSceneObjectType;
import mr.robotto.core.data.commons.MrObjectData;
import mr.robotto.core.data.commons.MrTransform;
import mr.robotto.core.data.commons.shader.MrShaderProgram;
import mr.robotto.core.data.containers.MrUniformKeyContainer;
import mr.robotto.core.data.model.mesh.MrMesh;

public class MrModelData extends MrObjectData {
    private MrMesh mMesh;
    private MrShaderProgram mShaderProgram;

    public MrModelData(String name, MrTransform transform, MrUniformKeyContainer uniformKeys, MrShaderProgram shaderProgram, MrMesh mesh) {
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
