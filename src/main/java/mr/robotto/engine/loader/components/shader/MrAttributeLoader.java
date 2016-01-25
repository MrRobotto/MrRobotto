/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.components.shader;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.engine.commons.MrDataType;
import mr.robotto.engine.components.shader.MrAttribute;
import mr.robotto.engine.loader.base.MrJsonBaseLoader;

/**
 * Loads a {@link MrAttribute} from JSON.
 */
public class MrAttributeLoader extends MrJsonBaseLoader<MrAttribute> {
    public MrAttributeLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrAttribute parse() throws JSONException {
        return new MrAttribute(loadAttributeType(), loadName(), loadIndex(), loadDataType());
    }

    private int loadAttributeType() throws JSONException {
        String upper = mRoot.getString("Attribute").toUpperCase();
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

    private MrDataType loadDataType() throws JSONException {
        String dataTypeStr = mRoot.getString("DataType");
        return MrDataType.valueOf(dataTypeStr.toUpperCase());
    }

    private int loadIndex() throws JSONException {
        return mRoot.getInt("Index");
    }

    private String loadName() throws JSONException {
        return mRoot.getString("Name");
    }
}
