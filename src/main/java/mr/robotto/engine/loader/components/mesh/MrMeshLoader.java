/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.components.mesh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mr.robotto.engine.components.mesh.MrBuffer;
import mr.robotto.engine.components.mesh.MrBufferKey;
import mr.robotto.engine.components.mesh.MrMesh;
import mr.robotto.engine.loader.base.MrJsonBaseLoader;

/**
 * Loads a {@link MrMesh} from JSON
 */
public class MrMeshLoader extends MrJsonBaseLoader<MrMesh> {

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

        int drawType = getDrawTypeFromString(drawTypeData);
        Map<Integer, MrBufferKey> keys = new HashMap<>();
        //MrHashMap<Integer, MrBufferKey> keys = new MrHashMap<>(new MrMapFunction<Integer, MrBufferKey>() {
        //    @Override
        //    public Integer getKeyOf(MrBufferKey mrBufferKey) {
        //        return mrBufferKey.getAttributeType();
        //    }
        //});
        //MrBuffer vertexBuffer = new MrVertexBuffer(vertexData.length());
        //MrBuffer indexBuffer = new MrIndexBuffer(indexData.length());
        MrBuffer vertexBuffer = MrBuffer.genVertexBuffer(vertexData.length());
        MrBuffer indexBuffer = MrBuffer.genIndexBuffer(indexData.length());

        loadVertexData(vertexBuffer, vertexData);
        loadIndexData(indexBuffer, indexData);
        loadKeys(keys, keysData);

        return new MrMesh(name, count, drawType, keys, vertexBuffer, indexBuffer);
    }

    //TODO: check all possibilities
    private int getDrawTypeFromString(String drawTypeStr) {
        if (drawTypeStr.equals("Triangles")) {
            return MrMesh.DRAWTYPE_TRIANGLES;
        } else {
            return MrMesh.DRAWTYPE_LINES;
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

    private void loadKeys(Map<Integer, MrBufferKey> list, JSONArray keys) throws JSONException {
        for (int i = 0; i < keys.length(); i++) {
            JSONObject jsonKey = keys.getJSONObject(i);
            MrBufferKeyLoader loader = new MrBufferKeyLoader(jsonKey);
            MrBufferKey key = loader.parse();
            //list.addAttributeKey(key);
            list.put(key.getAttributeType(), key);
        }
    }
}
