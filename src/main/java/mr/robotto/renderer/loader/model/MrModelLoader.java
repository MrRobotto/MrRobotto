/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.loader.model;

import mr.robotto.renderer.core.data.resources.mesh.MrMesh;
import mr.robotto.renderer.core.data.MrModelData;
import mr.robotto.renderer.loader.MrObjectLoader;
import mr.robotto.renderer.loader.model.shader.MrShaderProgramLoader;
import mr.robotto.renderer.core.data.resources.uniformkeys.MrUniformKeyList;
import mr.robotto.renderer.core.data.resources.shaders.MrShaderProgram;
import mr.robotto.renderer.core.data.resources.shaders.MrUniformType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MrModelLoader extends MrObjectLoader
{
    public MrModelLoader(JSONObject obj)
    {
        super(obj);
    }

    @Override
    public MrModelData parse() throws JSONException
    {
        MrModelData model = new MrModelData(getName(), getTransform(), getUniformKeyList(), getShaderProgram(), getMesh());
        return model;
    }

    private MrMesh getMesh() throws JSONException {
        JSONObject meshJson = mRoot.getJSONObject("Mesh");
        MrMeshLoader meshLoader = new MrMeshLoader(meshJson);
        return meshLoader.parse();
    }

    //TODO: This must be changed, the way you insert elements in the list
    private MrUniformKeyList getUniformKeyList() throws JSONException {
        MrUniformKeyList uniformKeyList = new MrUniformKeyList();
        JSONArray jsonUniformKeyList = mRoot.getJSONArray("UniformKeys");
        for (int i = 0; i < jsonUniformKeyList.length(); i++) {
            String strUniformType = jsonUniformKeyList.getString(i).toUpperCase();
            MrUniformType uniformType = MrUniformType.valueOf(strUniformType);
            uniformKeyList.addKey(uniformType);
        }
        return uniformKeyList;
    }

    private MrShaderProgram getShaderProgram() throws JSONException {
        JSONObject shaderProgramJson = mRoot.getJSONObject("ShaderProgram");
        MrShaderProgramLoader shaderProgramLoader = new MrShaderProgramLoader(shaderProgramJson);
        return shaderProgramLoader.parse();
    }
}
