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

import mr.robotto.engine.linearalgebra.MrVector4f;
import mr.robotto.sceneobjects.MrScene;

public class MrSceneLoader extends MrBaseObjectLoader {
    public MrSceneLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrScene parse() throws JSONException {
        return new MrScene.Builder()
                .setName(loadName())
                .setTransform(loadTransform())
                .setShaderProgram(loadShaderProgram())
                .setUniformKeySchemas(loadUniformKeySchemaList())
                .setClearColor(getClearColor())
                .build();
    }

    private MrVector4f getClearColor() throws JSONException {
        JSONArray colorArray = mRoot.getJSONArray("ClearColor");
        MrVector4f clearColor = new MrVector4f();
        clearColor.w = (float) colorArray.getDouble(0);
        clearColor.x = (float) colorArray.getDouble(1);
        clearColor.y = (float) colorArray.getDouble(2);
        clearColor.z = (float) colorArray.getDouble(3);
        return clearColor;
    }
}
