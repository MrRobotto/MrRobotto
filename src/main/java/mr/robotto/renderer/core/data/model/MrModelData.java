/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.data.model;

import mr.robotto.renderer.core.data.object.keys.MrUniformKeyList;
import mr.robotto.renderer.core.data.object.MrObjectData;
import mr.robotto.renderer.core.data.commons.MrSceneObjType;
import mr.robotto.renderer.core.data.model.mesh.MrMesh;
import mr.robotto.renderer.core.view.objectrenderers.MrObjectRender;
import mr.robotto.renderer.shaders.MrShaderProgram;
import mr.robotto.renderer.transform.MrTransform;

public class MrModelData extends MrObjectData
{
    private MrMesh mesh;

    private MrShaderProgram shaderProgram;

    public MrModelData(String name, MrTransform transform, MrUniformKeyList uniformKeys, MrShaderProgram shaderProgram, MrObjectRender renderer, MrMesh mesh) {
        super(name, MrSceneObjType.MODEL, transform, uniformKeys, shaderProgram, renderer);
        this.mesh = mesh;
        setRenderer(renderer);
    }

    public MrMesh getMesh() {
        return mesh;
    }
}
