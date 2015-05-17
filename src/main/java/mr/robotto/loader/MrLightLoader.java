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

import mr.robotto.core.MrLight;
import mr.robotto.core.MrObject;
import mr.robotto.linearalgebra.MrQuaternion;
import mr.robotto.linearalgebra.MrVector3f;
import mr.robotto.linearalgebra.MrVector4f;

/**
 * Created by aaron on 17/05/2015.
 */
public class MrLightLoader extends MrBaseObjectLoader {
    public MrLightLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrObject parse() throws JSONException {
        return new MrLight(getName(), getTransform(), getShaderProgram(), getUniformKeyList(), getLightColor());
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
