/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.loader.model;

import mr.robotto.renderer.data.model.mesh.MrMeshDrawType;
import mr.robotto.renderer.data.model.mesh.MrMesh;
import mr.robotto.renderer.data.model.mesh.buffers.MrBuffer;
import mr.robotto.renderer.data.model.mesh.buffers.MrIndexBuffer;
import mr.robotto.renderer.data.model.mesh.buffers.MrVertexBuffer;
import mr.robotto.renderer.data.model.mesh.keys.MrAttributeKey;
import mr.robotto.renderer.data.model.mesh.keys.MrAttributeKeyList;
import mr.robotto.renderer.loader.MrAbstractLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MrMeshLoader extends MrAbstractLoader<MrMesh>
{

    public MrMeshLoader(JSONObject obj)
    {
        super(obj);
    }

    @Override
    public MrMesh parse() throws JSONException
    {
        int count            = root.getInt("Count");
        String drawTypeData  = root.getString("DrawType");
        String name          = root.getString("Name");
        JSONArray vertexData = root.getJSONArray("VertexData");
        JSONArray indexData  = root.getJSONArray("IndexData");
        JSONArray keysData   = root.getJSONArray("AttributeKeys");

        MrMeshDrawType drawType = getDrawTypeFromString(drawTypeData);
        MrAttributeKeyList keys = new MrAttributeKeyList();
        MrBuffer vertexBuffer = new MrVertexBuffer(vertexData.length());
        MrBuffer indexBuffer = new MrIndexBuffer(indexData.length());

        loadVertexData(vertexBuffer, vertexData);
        loadIndexData(indexBuffer, indexData);
        loadKeys(keys, keysData);

        return new MrMesh(name, count,drawType,keys,vertexBuffer,indexBuffer);
    }


    private MrMeshDrawType getDrawTypeFromString(String drawTypeStr)
    {
        if (drawTypeStr.equals("Triangles"))
        {
            return MrMeshDrawType.TRIANGLES;
        }
        else
        {
            return MrMeshDrawType.LINES;
        }

    }

    private void loadVertexData(MrBuffer buffer, JSONArray array) throws JSONException
    {
        for (int i = 0; i < array.length(); i++)
        {
            buffer.putFloat((float) array.getDouble(i));
        }
    }

    private void loadIndexData(MrBuffer buffer, JSONArray array) throws JSONException
    {
        for (int i = 0; i < array.length(); i++)
        {
            buffer.putShort((short) array.getInt(i));
        }
    }

    private void loadKeys(MrAttributeKeyList list, JSONArray keys) throws JSONException
    {
        for (int i = 0; i < keys.length(); i++)
        {
            JSONObject jsonKey = keys.getJSONObject(i);
            MrAttributeKeyLoader loader = new MrAttributeKeyLoader(jsonKey);
            MrAttributeKey key = loader.parse();
            list.addAttributeKey(key);
        }
    }
}
