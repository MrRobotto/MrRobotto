/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.components;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import mr.robotto.MrRobotto;
import mr.robotto.components.comp.MrTexture;
import mr.robotto.components.data.material.MrMaterial;
import mr.robotto.components.data.material.MrMaterialLight;
import mr.robotto.linearalgebra.MrVector4f;
import mr.robotto.loader.MrBaseLoader;

/**
 * Created by aaron on 16/03/2015.
 */
public class MrMaterialLoader extends MrBaseLoader<MrMaterial> {

    public MrMaterialLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrMaterial parse() throws JSONException {
        String name = mRoot.getString("Name");
        JSONObject ambientJson = mRoot.getJSONObject("Ambient");
        JSONObject diffuseJson = mRoot.getJSONObject("Diffuse");
        JSONObject specularJson = mRoot.getJSONObject("Specular");
        MrMaterialLight ambient = loadMaterialLight(ambientJson);
        MrMaterialLight diffuse = loadMaterialLight(diffuseJson);
        MrMaterialLight specular = loadMaterialLight(specularJson);
        MrTexture texture = loadTexture();
        return new MrMaterial(name, ambient, diffuse, specular, texture);
    }

    private MrMaterialLight loadMaterialLight(JSONObject materialLightJson) throws JSONException {
        MrMaterialLight materialLight;
        MrVector4f color = loadColor(materialLightJson);
        float intensity = (float) materialLightJson.getDouble("Intensity");
        materialLight = new MrMaterialLight(intensity, color);
        return materialLight;
    }

    private MrVector4f loadColor(JSONObject materialLightJson) throws JSONException {
        MrVector4f color = new MrVector4f();
        JSONArray colorJson = materialLightJson.getJSONArray("Color");
        color.w = (float) colorJson.getDouble(0);
        color.x = (float) colorJson.getDouble(1);
        color.y = (float) colorJson.getDouble(2);
        color.z = (float) colorJson.getDouble(3);
        return color;
     }

    private MrTexture loadTexture() throws JSONException {
        JSONObject textureJson = mRoot.optJSONObject("Texture");
        if (textureJson == null)
            return null;
        MrTextureLoader loader = new MrTextureLoader(textureJson);
        return loader.parse();
    }

}
