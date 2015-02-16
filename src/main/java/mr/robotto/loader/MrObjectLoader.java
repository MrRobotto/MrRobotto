/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.core.MrSceneObjectType;
import mr.robotto.core.data.MrObjectData;
import mr.robotto.linearalgebra.MrTransform;
import mr.robotto.loader.model.MrModelLoader;

//TODO: Aqui falta mucho trabajo de control de errores
public class MrObjectLoader extends MrAbstractLoader<MrObjectData> {
    public MrObjectLoader(JSONObject obj) {
        super(obj);
    }

    protected String getName() throws JSONException {
        return mRoot.getString("Name");
    }

    protected MrTransform getTransform() throws JSONException {
        JSONObject transformJson = mRoot.getJSONObject("Transform");
        MrTransformLoader transformLoader = new MrTransformLoader(transformJson);
        return transformLoader.parse();
    }

    protected MrSceneObjectType getSceneObjType() throws JSONException {
        String typeStr = mRoot.getString("Type");
        return MrSceneObjectType.valueOf(typeStr.toUpperCase());
    }

    //TODO: Camera
    @Override
    public MrObjectData parse() throws JSONException {
        switch (getSceneObjType()) {
            case MODEL:
                MrModelLoader modelLoader = new MrModelLoader(mRoot);
                return modelLoader.parse();
            case SCENE:
                MrSceneLoader sceneLoader = new MrSceneLoader(mRoot);
                return sceneLoader.parse();
            case CAMERA:
                return null;
        }
        return null;
    }
}
