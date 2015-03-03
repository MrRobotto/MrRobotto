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

import mr.robotto.linearalgebra.MrQuaternion;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.linearalgebra.MrVector3f;

public class MrTransformLoader extends MrBaseLoader<MrTransform> {
    public MrTransformLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrTransform parse() throws JSONException {
        JSONArray locationData = mRoot.getJSONArray("Location");
        JSONArray rotationData = mRoot.getJSONArray("Rotation");
        JSONArray scaleData = mRoot.getJSONArray("Scale");
        MrTransform transform = new MrTransform();

        loadLocation(transform.getLocation(), locationData);
        loadRotation(transform.getRotation(), rotationData);
        loadScale(transform.getScale(), scaleData);

        return transform;
    }

    private void loadLocation(MrVector3f v, JSONArray loc) throws JSONException {
        int index = 0;
        v.x = (float) loc.getDouble(index);
        index++;
        v.y = (float) loc.getDouble(index);
        index++;
        v.z = (float) loc.getDouble(index);
    }

    private void loadRotation(MrQuaternion q, JSONArray rot) throws JSONException {
        int index = 0;
        q.w = (float) rot.getDouble(index);
        index++;
        q.x = (float) rot.getDouble(index);
        index++;
        q.y = (float) rot.getDouble(index);
        index++;
        q.z = (float) rot.getDouble(index);
        index++;
    }

    private void loadScale(MrVector3f s, JSONArray sca) throws JSONException {
        int index = 0;
        s.x = (float) sca.getDouble(index);
        index++;
        s.y = (float) sca.getDouble(index);
        index++;
        s.z = (float) sca.getDouble(index);
    }
}
