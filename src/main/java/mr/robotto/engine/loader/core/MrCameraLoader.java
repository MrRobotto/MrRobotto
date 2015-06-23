/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.core;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.engine.components.data.lens.MrPerspectiveLens;
import mr.robotto.engine.linearalgebra.MrVector3f;
import mr.robotto.sceneobjects.MrCamera;
import mr.robotto.sceneobjects.MrObject;

/**
 * Created by aaron on 03/03/2015.
 */
public class MrCameraLoader extends MrBaseObjectLoader {
    public MrCameraLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrObject parse() throws JSONException {
        //TODO: Check all this, the unsupported operation and that null return
        JSONObject lensJson = mRoot.getJSONObject("Lens");
        if (lensJson.getString("Type").equals("Perspective")) {
            return new MrCamera.Builder()
                    .setName(getName())
                    .setTransform(getTransform())
                    .setUniformKeys(getUniformKeyList())
                    .setShaderProgram(getShaderProgram())
                    .setLens(getPerspectiveLens(lensJson)).createCamera();
        } else if (lensJson.getString("Type").equals("Orthographic")) {
            //TODO: Make this
            throw new UnsupportedOperationException("Not implemented yet");
        } else {
            return null;
        }
    }

    private MrVector3f getLookAtVector(JSONObject cameraJson) {
        return null;
    }

    private MrVector3f getUpVector(JSONObject cameraJson) {
        return null;
    }

    private MrPerspectiveLens getPerspectiveLens(JSONObject jsonLens) throws JSONException {
        float aspectRatio = (float) jsonLens.getDouble("AspectRatio");
        float clipEnd = (float) jsonLens.getDouble("ClipEnd");
        float clipStart = (float) jsonLens.getDouble("ClipStart");
        float fov = (float) jsonLens.getDouble("FOV");
        return new MrPerspectiveLens(fov, aspectRatio, clipStart, clipEnd);
    }
}
