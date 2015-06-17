/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mr.robotto.components.comp.MrShaderProgram;
import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.core.MrObject;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.loader.components.MrTransformLoader;
import mr.robotto.loader.components.shader.MrShaderProgramLoader;

/**
 * Created by aaron on 03/03/2015.
 */
abstract class MrBaseObjectLoader extends MrJsonBaseLoader<MrObject> {
    public MrBaseObjectLoader(JSONObject obj) {
        super(obj);
    }

    protected String getName() throws JSONException {
        return mRoot.getString("Name");
    }

    protected MrTransform getTransform() throws JSONException {
        JSONObject transformJson = mRoot.getJSONObject("Transform");
        MrTransformLoader transformLoader = new MrTransformLoader(transformJson);
        return transformLoader.parse();
    }

    protected MrSceneObjectType getSceneObjType() throws JSONException {
        String typeStr = mRoot.getString("Type");
        return MrSceneObjectType.valueOf(typeStr.toUpperCase());
    }

    protected MrShaderProgram getShaderProgram() throws JSONException {
        if (mRoot.isNull("ShaderProgram")) {
            return null;
        }
        JSONObject shaderProgramJson = mRoot.getJSONObject("ShaderProgram");
        MrShaderProgramLoader shaderProgramLoader = new MrShaderProgramLoader(shaderProgramJson);
        return shaderProgramLoader.parse();
    }

    //TODO: This must be changed, the way you insert elements in the list
    protected Map<String, MrUniformKey> getUniformKeyList() throws JSONException {
        //MrUniformKeyMap uniformKeyList = new MrUniformKeyMap();
        HashMap<String, MrUniformKey> uniformKeyList = new HashMap<>();
        JSONArray jsonUniformKeyList = mRoot.getJSONArray("UniformKeys");
        for (int i = 0; i < jsonUniformKeyList.length(); i++) {
            JSONObject uniformKeyJson = jsonUniformKeyList.getJSONObject(i);
            String uniformType = uniformKeyJson.getString("Uniform");
            String generator = uniformKeyJson.getString("Generator");
            int level = uniformKeyJson.getInt("Level");
            MrUniformKey uniformKey = new MrUniformKey(generator, uniformType, level);
            uniformKeyList.put(uniformType, uniformKey);
        }
        return uniformKeyList;
    }
}
