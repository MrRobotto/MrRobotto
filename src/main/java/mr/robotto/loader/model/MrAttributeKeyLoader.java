/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.model;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.commons.MrDataType;
import mr.robotto.core.data.commons.shader.MrAttribute;
import mr.robotto.core.data.model.mesh.MrBufferKey;
import mr.robotto.loader.MrAbstractLoader;

public class MrAttributeKeyLoader extends MrAbstractLoader<MrBufferKey> {
    public MrAttributeKeyLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrBufferKey parse() throws JSONException {
        String attrstr = mRoot.getString("Attribute");
        int attribute = getAttributeTypeFromString(attrstr);
        String name = mRoot.getString("Name");
        int index = mRoot.getInt("Index");
        String dataTypeStr = mRoot.getString("DataType");
        int size = mRoot.getInt("Size");
        MrDataType dataType = getDataTypeFromString(dataTypeStr);
        int stride = mRoot.getInt("Stride");
        int pointer = mRoot.getInt("Pointer");

        return new MrBufferKey(attribute, dataType, size, stride, pointer);
    }

    private int getAttributeTypeFromString(String attrstr) {
        String upper = attrstr.toUpperCase();
        switch (upper) {
            case "VERTICES":
                return MrAttribute.VERTICES;
            case "NORMALS":
                return MrAttribute.NORMALS;
            case "COLOR":
                return MrAttribute.COLOR;
            case "MATERIALINDEX":
                return MrAttribute.MATERIAL_INDEX;
            case "TEXTURE":
                return MrAttribute.TEXTURE;
            case "WEIGHT":
                return MrAttribute.WEIGHT;
            case "BONEINDICES":
                return MrAttribute.BONE_INDICES;
            default:
                return -1;
        }
    }

    private MrDataType getDataTypeFromString(String dataTypeStr) {
        return MrDataType.valueOf(dataTypeStr.toUpperCase());
    }
}
