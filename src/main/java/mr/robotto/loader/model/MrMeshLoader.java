/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.core.data.containers.MrBufferKeyContainer;
import mr.robotto.core.data.model.mesh.MrBuffer;
import mr.robotto.core.data.model.mesh.MrBufferKey;
import mr.robotto.core.data.model.mesh.MrIndexBuffer;
import mr.robotto.core.data.model.mesh.MrMesh;
import mr.robotto.core.data.model.mesh.MrVertexBuffer;
import mr.robotto.core.data.types.MrMeshDrawType;
import mr.robotto.loader.MrAbstractLoader;

public class MrMeshLoader extends MrAbstractLoader<MrMesh> {

    public MrMeshLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrMesh parse() throws JSONException {
        int count = mRoot.getInt("Count");
        String drawTypeData = mRoot.getString("DrawType");
        String name = mRoot.getString("Name");
        JSONArray vertexData = mRoot.getJSONArray("VertexData");
        JSONArray indexData = mRoot.getJSONArray("IndexData");
        JSONArray keysData = mRoot.getJSONArray("AttributeKeys");

        MrMeshDrawType drawType = getDrawTypeFromString(drawTypeData);
        MrBufferKeyContainer keys = new MrBufferKeyContainer();
        MrBuffer vertexBuffer = new MrVertexBuffer(vertexData.length());
        MrBuffer indexBuffer = new MrIndexBuffer(indexData.length());

        loadVertexData(vertexBuffer, vertexData);
        loadIndexData(indexBuffer, indexData);
        loadKeys(keys, keysData);

        return new MrMesh(name, count, drawType, keys, vertexBuffer, indexBuffer);
    }


    private MrMeshDrawType getDrawTypeFromString(String drawTypeStr) {
        if (drawTypeStr.equals("Triangles")) {
            return MrMeshDrawType.TRIANGLES;
        } else {
            return MrMeshDrawType.LINES;
        }

    }

    private void loadVertexData(MrBuffer buffer, JSONArray array) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            buffer.putFloat((float) array.getDouble(i));
        }
    }

    private void loadIndexData(MrBuffer buffer, JSONArray array) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            buffer.putShort((short) array.getInt(i));
        }
    }

    private void loadKeys(MrBufferKeyContainer list, JSONArray keys) throws JSONException {
        for (int i = 0; i < keys.length(); i++) {
            JSONObject jsonKey = keys.getJSONObject(i);
            MrAttributeKeyLoader loader = new MrAttributeKeyLoader(jsonKey);
            MrBufferKey key = loader.parse();
            //list.addAttributeKey(key);
            list.add(key);
        }
    }
}
