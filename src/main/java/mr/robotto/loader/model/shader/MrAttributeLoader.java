/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.model.shader;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.commons.MrDataType;
import mr.robotto.core.data.resources.shaders.input.MrAttribute;
import mr.robotto.core.data.resources.shaders.input.MrAttributeType;
import mr.robotto.loader.MrAbstractLoader;

public class MrAttributeLoader extends MrAbstractLoader<MrAttribute> {
    public MrAttributeLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrAttribute parse() throws JSONException {
        return new MrAttribute(getAttributeType(), getName(), getIndex(), getDataType());
    }

    private MrAttributeType getAttributeType() throws JSONException {
        String attribTypeStr = mRoot.getString("Attribute");
        return MrAttributeType.valueOf(attribTypeStr.toUpperCase());
    }

    private MrDataType getDataType() throws JSONException {
        String dataTypeStr = mRoot.getString("DataType");
        return MrDataType.valueOf(dataTypeStr.toUpperCase());
    }

    private int getIndex() throws JSONException {
        return mRoot.getInt("Index");
    }

    private String getName() throws JSONException {
        return mRoot.getString("Name");
    }
}
