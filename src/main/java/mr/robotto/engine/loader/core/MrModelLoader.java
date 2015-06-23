/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.engine.components.comp.MrMesh;
import mr.robotto.engine.components.data.material.MrMaterial;
import mr.robotto.engine.components.data.skeleton.MrSkeleton;
import mr.robotto.engine.loader.components.material.MrMaterialLoader;
import mr.robotto.engine.loader.components.mesh.MrMeshLoader;
import mr.robotto.engine.loader.components.skeleton.MrSkeletonLoader;
import mr.robotto.sceneobjects.MrModel;

public class MrModelLoader extends MrBaseObjectLoader {
    public MrModelLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrModel parse() throws JSONException {
        return new MrModel.Builder()
                .setName(getName())
                .setTransform(getTransform())
                .setUniformKeys(getUniformKeyList())
                .setShaderProgram(getShaderProgram())
                .setMesh(loadMesh())
                .setMaterials(loadMaterials())
                .setSkeleton(loadSkeleton())
                .createModel();
    }

    private MrMesh loadMesh() throws JSONException {
        JSONObject meshJson = mRoot.getJSONObject("Mesh");
        MrMeshLoader meshLoader = new MrMeshLoader(meshJson);
        return meshLoader.parse();
    }

    private MrMaterial[] loadMaterials() throws JSONException {
        JSONArray materialsJson = mRoot.getJSONArray("Materials");
        MrMaterial[] materials = new MrMaterial[materialsJson.length()];
        for (int i = 0; i < materialsJson.length(); i++) {
            JSONObject matJson = materialsJson.getJSONObject(i);
            MrMaterialLoader materialLoader = new MrMaterialLoader(matJson);
            MrMaterial material = materialLoader.parse();
            materials[i] = material;
        }
        return materials;
    }

    private MrSkeleton loadSkeleton() throws JSONException {
        JSONObject skeletonJson = mRoot.optJSONObject("Skeleton");
        if (skeletonJson != null) {
            MrSkeletonLoader loader = new MrSkeletonLoader(skeletonJson);
            return loader.parse();
        } else {
            return null;
        }
    }
}
