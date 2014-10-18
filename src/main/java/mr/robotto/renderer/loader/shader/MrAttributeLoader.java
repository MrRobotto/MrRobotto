/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.loader.shader;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.renderer.commons.MrDataType;
import mr.robotto.renderer.loader.MrAbstractLoader;
import mr.robotto.renderer.shaders.MrAttribute;
import mr.robotto.renderer.shaders.MrAttributeType;

public class MrAttributeLoader extends MrAbstractLoader<MrAttribute> {
    public MrAttributeLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrAttribute parse() throws JSONException {
        return new MrAttribute(getAttributeType(), getName(), getIndex(), getDataType());
    }

    private MrAttributeType getAttributeType() throws JSONException {
        String attribTypeStr = root.getString("Attribute");
        return MrAttributeType.valueOf(attribTypeStr.toUpperCase());
    }

    private MrDataType getDataType() throws JSONException {
        String dataTypeStr = root.getString("DataType");
        return MrDataType.valueOf(dataTypeStr.toUpperCase());
    }

    private int getIndex() throws JSONException {
        return root.getInt("Index");
    }

    private String getName() throws JSONException {
        return root.getString("Name");
    }
}
