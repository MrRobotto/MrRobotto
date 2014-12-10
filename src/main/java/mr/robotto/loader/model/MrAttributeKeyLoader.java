/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.model;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.commons.MrDataType;
import mr.robotto.core.data.resources.mesh.bufferkeys.MrBufferKey;
import mr.robotto.core.data.resources.shaders.input.attributes.MrAttributeType;
import mr.robotto.loader.MrAbstractLoader;

public class MrAttributeKeyLoader extends MrAbstractLoader<MrBufferKey> {
    public MrAttributeKeyLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrBufferKey parse() throws JSONException {
        String attrstr = mRoot.getString("Attribute");
        MrAttributeType attribute = getAttributeTypeFromString(attrstr);
        String name = mRoot.getString("Name");
        int index = mRoot.getInt("Index");
        String dataTypeStr = mRoot.getString("DataType");
        int size = mRoot.getInt("Size");
        MrDataType dataType = getDataTypeFromString(dataTypeStr);
        int stride = mRoot.getInt("Stride");
        int pointer = mRoot.getInt("Pointer");

        return new MrBufferKey(attribute, dataType, size, stride, pointer);
    }

    private MrAttributeType getAttributeTypeFromString(String attrstr) {
        return MrAttributeType.valueOf(attrstr.toUpperCase());
    }

    private MrDataType getDataTypeFromString(String dataTypeStr) {
        return MrDataType.valueOf(dataTypeStr.toUpperCase());
    }
}
