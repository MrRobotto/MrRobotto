/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.loader;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mr.robotto.engine.components.material.MrTexture;
import mr.robotto.engine.components.shader.MrShaderProgram;

/**
 * Temporal storage class for resources
 */
public class MrResources {

    private static MrResources sResources = new MrResources();

    private HashMap<String, MrTexture> mTextures;
    private HashMap<String, MrShaderProgram> mPrograms;
    private ConcurrentHashMap<String, Bitmap> mTextureBitmaps;

    private MrResources() {
        mTextures = new HashMap<>();
        mPrograms = new HashMap<>();
        mTextureBitmaps = new ConcurrentHashMap<>();
    }

    public static MrResources getInstance() {
        return sResources;
    }

    /**
     * Gets the loaded textures
     *
     * @return map of texture name - texture
     */
    public HashMap<String, MrTexture> getTextures() {
        return mTextures;
    }

    /**
     * Gets a certain texture
     * @param name texture name
     * @return the texture, null if it does not exist
     */
    public MrTexture getTexture(String name) {
        return mTextures.get(name);
    }

    /**
     * Adds a new texture to resources
     * @param texture the new texture
     */
    public void addTexture(MrTexture texture) {
        mTextures.put(texture.getName(), texture);
    }

    /**
     * Gets a certain shader program
     * @param name shader program name
     * @return the shader program, null if it does not exist
     */
    public MrShaderProgram getProgram(String name) {
        return mPrograms.get(name);
    }

    /**
     * Gets the loaded shader programs
     * @return a map of shader program name - shader program
     */
    public HashMap<String, MrShaderProgram> getPrograms() {
        return mPrograms;
    }

    /**
     * Adds a new shader program to resources
     * @param program shader program to be added
     */
    public void addShaderProgram(MrShaderProgram program) {
        mPrograms.put(program.getName(), program);
    }

    /**
     * Gets the loaded bitmaps
     * @return a map of bitmap file name - bitmap
     */
    public Map<String, Bitmap> getTextureBitmaps() {
        return mTextureBitmaps;
    }

    /**
     * Adds a new bitmap to resources
     * @param texName bitmap filename
     * @param bitmap bitmap
     */
    public void addTextureBitmap(String texName, Bitmap bitmap) {
        mTextureBitmaps.put(texName, bitmap);
    }

    /**
     * Releases all elements in resources
     */
    public void freeResources() {
        for (Bitmap bitmap : getTextureBitmaps().values()) {
            bitmap.recycle();
        }
        mTextureBitmaps.clear();
        mPrograms.clear();
        mTextures.clear();
    }
}
