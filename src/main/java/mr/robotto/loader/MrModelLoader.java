/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.components.data.mesh.MrMesh;
import mr.robotto.components.data.shader.MrShaderProgram;
import mr.robotto.core.data.MrModelData;
import mr.robotto.loader.components.MrMeshLoader;
import mr.robotto.loader.components.shader.MrShaderProgramLoader;

public class MrModelLoader extends MrBaseObjectLoader {
    public MrModelLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrModelData parse() throws JSONException {
        MrModelData model = new MrModelData(getName(), getTransform(), getUniformKeyList(), getShaderProgram(), getMesh());
        return model;
    }

    private MrMesh getMesh() throws JSONException {
        JSONObject meshJson = mRoot.getJSONObject("Mesh");
        MrMeshLoader meshLoader = new MrMeshLoader(meshJson);
        return meshLoader.parse();
    }
}