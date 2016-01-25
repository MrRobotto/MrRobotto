/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.components.uniformkey;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.engine.components.uniformkey.MrUniformKeySchema;
import mr.robotto.engine.loader.base.MrJsonBaseLoader;

/**
 * Created by aaron on 25/01/2016.
 */
public class MrUniformKeySchemaLoader extends MrJsonBaseLoader<MrUniformKeySchema> {
    public MrUniformKeySchemaLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrUniformKeySchema parse() throws JSONException {
        String uniformType = mRoot.getString("Uniform");
        String generator = mRoot.getString("Generator");
        int count = mRoot.getInt("Count");
        int level = mRoot.getInt("Level");
        MrUniformKeySchema schema = new MrUniformKeySchema(uniformType, level, generator, count);
        return schema;
    }
}
