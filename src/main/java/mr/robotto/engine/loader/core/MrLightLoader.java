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

import mr.robotto.engine.linearalgebra.MrVector3f;
import mr.robotto.sceneobjects.MrLight;
import mr.robotto.sceneobjects.MrObject;

/**
 * Created by aaron on 17/05/2015.
 */
public class MrLightLoader extends MrBaseObjectLoader {
    public MrLightLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrObject parse() throws JSONException {
        return new MrLight.Builder()
                .setName(getName())
                .setTransform(getTransform())
                .setShaderProgram(getShaderProgram())
                .setUniformKeys(getUniformKeyList())
                .setLightColor(getLightColor())
                .createLight();
    }

    private MrVector3f getLightColor() throws JSONException {
        MrVector3f v = new MrVector3f();
        JSONArray color = mRoot.getJSONArray("Color");
        loadLightColor(v, color);
        return v;
    }

    private void loadLightColor(MrVector3f v, JSONArray rot) throws JSONException {
        int index = 0;
        v.x = (float) rot.getDouble(index);
        index++;
        v.y = (float) rot.getDouble(index);
        index++;
        v.z = (float) rot.getDouble(index);
    }
}
