/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mr.robotto.components.comp.MrTexture;

/**
 * Created by aaron on 03/05/2015.
 */
public class MrResources {

    private HashMap<String, MrTexture> mTextures;
    private ConcurrentHashMap<String, Bitmap> mTextureBitmaps;

    public MrResources() {
        mTextures = new HashMap<>();
        mTextureBitmaps = new ConcurrentHashMap<>();
    }

    public HashMap<String, MrTexture> getTextures() {
        return mTextures;
    }

    public void addTexture(MrTexture texture) {
        mTextures.put(texture.getName(), texture);
    }

    public Map<String, Bitmap> getTextureBitmaps() {
        return mTextureBitmaps;
    }

    public void addTextureBitmap(String texName, Bitmap bitmap) {
        mTextureBitmaps.put(texName, bitmap);
    }
}
