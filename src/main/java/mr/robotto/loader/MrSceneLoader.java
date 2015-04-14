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

import mr.robotto.core.controller.MrScene;
import mr.robotto.core.data.MrSceneData;
import mr.robotto.linearalgebra.MrVector4f;

public class MrSceneLoader extends MrBaseObjectLoader {
    public MrSceneLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrScene parse() throws JSONException {
        return new MrScene(getName(), getTransform(), getShaderProgram(), getUniformKeyList(), getClearColor());
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
