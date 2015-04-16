/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.components.data.material.MrMaterial;
import mr.robotto.components.data.material.MrMaterialMap;
import mr.robotto.components.comp.MrMesh;
import mr.robotto.core.MrModel;
import mr.robotto.loader.components.MrMaterialLoader;
import mr.robotto.loader.components.MrMeshLoader;

public class MrModelLoader extends MrBaseObjectLoader {
    public MrModelLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrModel parse() throws JSONException {
        return new MrModel(getName(), getTransform(), getUniformKeyList(), getShaderProgram(), getMesh(), getMaterials());
    }

    private MrMesh getMesh() throws JSONException {
        JSONObject meshJson = mRoot.getJSONObject("Mesh");
        MrMeshLoader meshLoader = new MrMeshLoader(meshJson);
        return meshLoader.parse();
    }

    private MrMaterialMap getMaterials() throws JSONException {
        MrMaterialMap materialMap = new MrMaterialMap();
        JSONArray materialsJson = mRoot.getJSONArray("Materials");
        for (int i = 0; i < materialsJson.length(); i++) {
            JSONObject matJson = materialsJson.getJSONObject(i);
            MrMaterialLoader materialLoader = new MrMaterialLoader(matJson);
            MrMaterial material = materialLoader.parse();
            materialMap.add(material);
        }
        return materialMap;
    }
}
