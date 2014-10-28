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
import mr.robotto.renderer.shaders.MrUniform;
import mr.robotto.renderer.shaders.MrUniformType;

public class MrUniformLoader extends MrAbstractLoader<MrUniform> {

    public MrUniformLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrUniform parse() throws JSONException {
        return new MrUniform(getUniformType(), getName(), getDataType(), getCount());
    }

    private int getCount() throws JSONException {
        return mRoot.getInt("Count");
    }

    private MrDataType getDataType() throws JSONException {
        String dataTypeStr = mRoot.getString("DataType");
        return MrDataType.valueOf(dataTypeStr.toUpperCase());
    }

    private String getName() throws JSONException {
        return mRoot.getString("Name");
    }

    private MrUniformType getUniformType() throws JSONException {
        String uniformTypeStr = mRoot.getString("Uniform");
        return MrUniformType.valueOf(uniformTypeStr.toUpperCase());
    }
}
