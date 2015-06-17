/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.components.skeleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.components.data.skeleton.MrBone;
import mr.robotto.linearalgebra.MrQuaternion;
import mr.robotto.linearalgebra.MrVector3f;
import mr.robotto.loader.core.MrJsonBaseLoader;

/**
 * Created by aaron on 27/04/2015.
 */
public class MrBoneLoader extends MrJsonBaseLoader<MrBone> {
    public MrBoneLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrBone parse() throws JSONException {
        String name = mRoot.getString("Name");
        JSONArray locJson = mRoot.getJSONArray("Location");
        JSONArray rotJson = mRoot.getJSONArray("Rotation");
        JSONArray scaJson = mRoot.getJSONArray("Scale");
        MrVector3f loc = new MrVector3f();
        MrQuaternion rot = new MrQuaternion();
        MrVector3f sca = new MrVector3f();
        loadLocation(loc, locJson);
        loadRotation(rot, rotJson);
        loadScale(sca, scaJson);
        return new MrBone(name, loc, rot, sca);
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
