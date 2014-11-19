/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.loader.model;

import mr.robotto.renderer.commons.MrDataType;
import mr.robotto.renderer.core.data.model.mesh.keys.MrAttributeKey;
import mr.robotto.renderer.loader.MrAbstractLoader;
import mr.robotto.renderer.core.data.model.shaders.MrAttributeType;

import org.json.JSONException;
import org.json.JSONObject;

public class MrAttributeKeyLoader extends MrAbstractLoader<MrAttributeKey>
{
    public MrAttributeKeyLoader(JSONObject obj)
    {
        super(obj);
    }

    @Override
    public MrAttributeKey parse() throws JSONException
    {
        String attrstr = mRoot.getString("Attribute");
        MrAttributeType attribute = getAttributeTypeFromString(attrstr);
        String name = mRoot.getString("Name");
        int index = mRoot.getInt("Index");
        String dataTypeStr = mRoot.getString("DataType");
        int size = mRoot.getInt("Size");
        MrDataType dataType = getDataTypeFromString(dataTypeStr);
        int stride = mRoot.getInt("Stride");
        int pointer = mRoot.getInt("Pointer");

        return new MrAttributeKey(attribute, name, index, dataType, size, stride, pointer);
    }

    private MrAttributeType getAttributeTypeFromString(String attrstr)
    {
        return MrAttributeType.valueOf(attrstr.toUpperCase());
    }

    private MrDataType getDataTypeFromString(String dataTypeStr)
    {
        return MrDataType.valueOf(dataTypeStr.toUpperCase());
    }
}
