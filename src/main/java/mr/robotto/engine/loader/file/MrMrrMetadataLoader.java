/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.file;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.engine.loader.base.MrJsonBaseLoader;

/**
 * Created by aaron on 03/11/2015.
 */
class MrMrrMetadataLoader extends MrJsonBaseLoader<MrMrrMetadata> {


    public MrMrrMetadataLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrMrrMetadata parse() throws JSONException {
        return null;
    }
}
