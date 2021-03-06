/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader.components.texture;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.MrRobottoEngine;
import mr.robotto.engine.components.MrTexture;
import mr.robotto.engine.loader.core.MrJsonBaseLoader;

/**
 * Created by aaron on 04/05/2015.
 */
public class MrTextureLoader extends MrJsonBaseLoader<MrTexture> {

    public MrTextureLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrTexture parse() throws JSONException {
        String name = loadName();
        MrTexture texture = MrRobottoEngine.getResources().getTexture(name);
        if (texture != null) {
            return texture;
        }
        texture = new MrTexture(loadName(), loadIndex(), loadMagFilter(), loadMinFilter(), loadBitmap());
        MrRobottoEngine.getResources().addTexture(texture);
        return texture;
    }

    private int loadIndex() throws JSONException {
        return mRoot.getInt("Index");
    }

    private int loadMagFilter() throws JSONException {
        String magFilter = mRoot.getString("MagFilter");
        if (magFilter.equalsIgnoreCase("Nearest")) {
            return MrTexture.MAG_FILTER_NEAREST;
        } else if (magFilter.equalsIgnoreCase("Linear")) {
            return MrTexture.MAG_FILTER_LINEAR;
        }
        return -1;
    }

    private int loadMinFilter() throws JSONException {
        String minFilter = mRoot.getString("MinFilter");
        if (minFilter.equalsIgnoreCase("Nearest")) {
            return MrTexture.MIN_FILTER_NEAREST;
        } else if (minFilter.equalsIgnoreCase("Linear")) {
            return MrTexture.MIN_FILTER_LINEAR;
        } else if (minFilter.equalsIgnoreCase("Nearest_Nearest")) {
            return MrTexture.MIN_FILTER_NEAREST_NEAREST;
        } else if (minFilter.equalsIgnoreCase("Nearest_Linear")) {
            return MrTexture.MIN_FILTER_NEAREST_LINEAR;
        } else if (minFilter.equalsIgnoreCase("Linear_Linear")) {
            return MrTexture.MIN_FILTER_LINEAR_LINEAR;
        } else if (minFilter.equalsIgnoreCase("Linear_Nearest")) {
            return MrTexture.MIN_FILTER_LINEAR_NEAREST;
        }
        return -1;
    }

    private String loadName() throws JSONException {
        return mRoot.getString("Name");
    }

    private Bitmap loadBitmap() throws JSONException {
        String name = loadName();
        return MrRobottoEngine.getResources().getTextureBitmaps().get(name);
    }
}
