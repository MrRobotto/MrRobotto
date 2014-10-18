/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.renderer.data.object.MrObjectData;
import mr.robotto.renderer.data.MrSceneObjType;
import mr.robotto.renderer.loader.model.MrModelLoader;
import mr.robotto.renderer.loader.shader.MrShaderProgramLoader;
import mr.robotto.renderer.proposed.MrUniformKeyList;
import mr.robotto.renderer.shaders.MrShaderProgram;
import mr.robotto.renderer.shaders.MrUniformType;
import mr.robotto.renderer.transform.MrTransform;

//TODO: Aqui falta mucho trabajo de control de errores
public class MrObjectLoader extends MrAbstractLoader<MrObjectData> {
    public MrObjectLoader(JSONObject obj) {
        super(obj);
    }

    protected String getName() throws JSONException {
        return root.getString("Name");
    }

    protected MrTransform getTransform() throws JSONException {
        JSONObject transformJson = root.getJSONObject("Transform");
        MrTransformLoader transformLoader = new MrTransformLoader(transformJson);
        return transformLoader.parse();
    }

    protected MrSceneObjType getSceneObjType() throws JSONException {
        String typeStr = root.getString("Type");
        return MrSceneObjType.valueOf(typeStr.toUpperCase());
    }

    protected boolean getActive() throws JSONException {
        return root.getBoolean("Active");
    }

    protected void parseChildren(MrObjectData object) throws JSONException {
        JSONArray children = root.getJSONArray("Children");
        for (int i = 0; i < children.length(); i++) {
            JSONObject childJson =  children.getJSONObject(i);
            MrObjectLoader objectLoader = new MrObjectLoader(childJson);
            MrObjectData child = objectLoader.parse();
            if (child != null) {
                object.addChild(child);
            }
        }
    }

    protected void parseAll(MrObjectData object) throws JSONException {
        object.setActive(getActive());
        parseChildren(object);
    }

    protected MrUniformKeyList getUniformKeyList() throws JSONException {
        MrUniformKeyList uniformKeyList = new MrUniformKeyList();
        JSONArray jsonUniformKeyList = root.getJSONArray("UniformKeys");
        for (int i = 0; i < jsonUniformKeyList.length(); i++) {
            String strUniformType = jsonUniformKeyList.getString(i).toUpperCase();
            MrUniformType uniformType = MrUniformType.valueOf(strUniformType);
            uniformKeyList.addKey(uniformType);
        }
        return uniformKeyList;
    }

    protected MrShaderProgram getShaderProgram() throws JSONException {
        JSONObject shaderProgramJson = root.getJSONObject("ShaderProgram");
        MrShaderProgramLoader shaderProgramLoader = new MrShaderProgramLoader(shaderProgramJson);
        return shaderProgramLoader.parse();
    }

    @Override
    public MrObjectData parse() throws JSONException {
        switch (getSceneObjType()) {
            case MODEL:
                MrModelLoader modelLoader = new MrModelLoader(root);
                return modelLoader.parse();
            case SCENE:
                MrSceneLoader sceneLoader = new MrSceneLoader(root);
                return sceneLoader.parse();
            case CAMERA:
                return null;
        }
        return null;
    }
}
