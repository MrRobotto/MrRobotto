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

import mr.robotto.core.MrObject;
import mr.robotto.core.MrSceneObjectType;

//TODO: Aqui falta mucho trabajo de control de errores
//TODO: Extiendo de esto para usar parse, pero aqui parse ya está implementado... meeeec
public class MrObjectLoader extends MrBaseLoader<MrObject> {
    public MrObjectLoader(JSONObject obj) {
        super(obj);
    }

    protected String getName() throws JSONException {
        return mRoot.getString("Name");
    }

    protected MrSceneObjectType getSceneObjType() throws JSONException {
        String typeStr = mRoot.getString("Type");
        return MrSceneObjectType.valueOf(typeStr.toUpperCase());
    }

    //TODO: Camera
    @Override
    public MrObject parse() throws JSONException {
        switch (getSceneObjType()) {
            case MODEL:
                MrModelLoader modelLoader = new MrModelLoader(mRoot);
                return modelLoader.parse();
            case SCENE:
                MrSceneLoader sceneLoader = new MrSceneLoader(mRoot);
                return sceneLoader.parse();
            case CAMERA:
                MrCameraLoader cameraLoader = new MrCameraLoader(mRoot);
                return cameraLoader.parse();
        }
        return null;
    }
}
