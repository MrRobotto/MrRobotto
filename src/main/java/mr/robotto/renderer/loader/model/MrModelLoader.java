/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.loader.model;

import mr.robotto.renderer.data.model.mesh.MrMesh;
import mr.robotto.renderer.data.model.MrModelData;
import mr.robotto.renderer.loader.MrObjectLoader;
import mr.robotto.renderer.renderer.rendereables.objectrenderers.MrModelRender;

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
        //MrModel model = new MrModel(getName(), getTransform(), getMesh(), getShaderProgram());
        MrModelData model = new MrModelData(getName(), getTransform(), getUniformKeyList(), getShaderProgram(), new MrModelRender(), getMesh());
        parseChildren(model);
        model.setActive(getActive());
        return model;
    }

    private MrMesh getMesh() throws JSONException {
        JSONObject meshJson = root.getJSONObject("Mesh");
        MrMeshLoader meshLoader = new MrMeshLoader(meshJson);
        return meshLoader.parse();
    }
}
