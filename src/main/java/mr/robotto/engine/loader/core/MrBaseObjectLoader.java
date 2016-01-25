/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import mr.robotto.engine.components.shader.MrShaderProgram;
import mr.robotto.engine.components.uniformkey.MrUniformKeySchema;
import mr.robotto.engine.core.MrSceneObjectType;
import mr.robotto.engine.linearalgebra.MrTransform;
import mr.robotto.engine.loader.base.MrJsonBaseLoader;
import mr.robotto.engine.loader.components.MrTransformLoader;
import mr.robotto.engine.loader.components.shader.MrShaderProgramLoader;
import mr.robotto.engine.loader.components.uniformkey.MrUniformKeySchemaLoader;
import mr.robotto.sceneobjects.MrObject;

/**
 * Created by aaron on 03/03/2015.
 */
abstract class MrBaseObjectLoader extends MrJsonBaseLoader<MrObject> {
    public MrBaseObjectLoader(JSONObject obj) {
        super(obj);
    }

    protected String loadName() throws JSONException {
        return mRoot.getString("Name");
    }

    protected MrTransform loadTransform() throws JSONException {
        JSONObject transformJson = mRoot.getJSONObject("Transform");
        MrTransformLoader transformLoader = new MrTransformLoader(transformJson);
        return transformLoader.parse();
    }

    protected MrSceneObjectType loadSceneObjType() throws JSONException {
        String typeStr = mRoot.getString("Type");
        return MrSceneObjectType.valueOf(typeStr.toUpperCase());
    }

    protected MrShaderProgram loadShaderProgram() throws JSONException {
        if (mRoot.isNull("ShaderProgram")) {
            return null;
        }
        JSONObject shaderProgramJson = mRoot.getJSONObject("ShaderProgram");
        MrShaderProgramLoader shaderProgramLoader = new MrShaderProgramLoader(shaderProgramJson);
        return shaderProgramLoader.parse();
    }

    //TODO: This must be changed, the way you insert elements in the list
    protected Collection<MrUniformKeySchema> loadUniformKeySchemaList() throws JSONException {
        //MrUniformKeyMap uniformKeyList = new MrUniformKeyMap();
        ArrayList<MrUniformKeySchema> schemas = new ArrayList<>();
        JSONArray jsonUniformKeyList = mRoot.getJSONArray("UniformKeySchemas");
        for (int i = 0; i < jsonUniformKeyList.length(); i++) {
            JSONObject uniformKeySchemaJson = jsonUniformKeyList.getJSONObject(i);
            MrUniformKeySchemaLoader loader = new MrUniformKeySchemaLoader(uniformKeySchemaJson);
            MrUniformKeySchema schema = loader.parse();
            schemas.add(schema);
        }
        return schemas;
    }
}
