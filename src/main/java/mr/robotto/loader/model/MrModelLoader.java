/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.core.data.commons.MrUniformKey;
import mr.robotto.core.data.commons.shader.MrShaderProgram;
import mr.robotto.core.data.containers.MrUniformKeyContainer;
import mr.robotto.core.data.model.MrModelData;
import mr.robotto.core.data.model.mesh.MrMesh;
import mr.robotto.loader.MrObjectLoader;
import mr.robotto.loader.model.shader.MrShaderProgramLoader;

public class MrModelLoader extends MrObjectLoader {
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

    //TODO: This must be changed, the way you insert elements in the list
    private MrUniformKeyContainer getUniformKeyList() throws JSONException {
        MrUniformKeyContainer uniformKeyList = new MrUniformKeyContainer();
        JSONArray jsonUniformKeyList = mRoot.getJSONArray("UniformKeys");
        for (int i = 0; i < jsonUniformKeyList.length(); i++) {
            String uniformType = jsonUniformKeyList.getString(i);
            //MrUniformType uniformType = MrUniformType.valueOf(strUniformType);
            //MrUniformKey uniformKey = new MrUniformKey(uniformType);
            MrUniformKey uniformKey = new MrUniformKey(uniformType);
            uniformKeyList.add(uniformKey);
        }
        return uniformKeyList;
    }

    private MrShaderProgram getShaderProgram() throws JSONException {
        JSONObject shaderProgramJson = mRoot.getJSONObject("ShaderProgram");
        MrShaderProgramLoader shaderProgramLoader = new MrShaderProgramLoader(shaderProgramJson);
        return shaderProgramLoader.parse();
    }
}
