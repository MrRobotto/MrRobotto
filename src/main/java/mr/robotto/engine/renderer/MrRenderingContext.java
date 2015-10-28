/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.renderer;


import java.util.HashMap;
import java.util.Map;

import mr.robotto.engine.components.material.MrTexture;
import mr.robotto.engine.components.mesh.MrMesh;
import mr.robotto.engine.components.shader.MrShaderProgram;
import mr.robotto.engine.components.uniformkey.MrUniformKey;
import mr.robotto.engine.scenetree.MrSceneTreeData;

/**
 * Created by Aarón on 28/11/2014.
 */
public class MrRenderingContext {
    private MrSceneTreeData mObjectsTree;
    private HashMap<String, MrUniformKey> mUniformKeys;
    private int mWidth;
    private int mHeight;

    private MrMesh mBoundMesh;
    private MrShaderProgram mBoundShaderProgram;
    private MrTexture mBoundTexture;

    public MrRenderingContext(MrSceneTreeData objectsTree) {
        mObjectsTree = objectsTree;
        mUniformKeys = new HashMap<>();
    }

    public MrSceneTreeData getObjectsTree() {
        return mObjectsTree;
    }


    public Map<String, MrUniformKey> getUniformKeys() {
        return mUniformKeys;
    }

    //TODO: Use this method to fill elements
    public void setWindowSize(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getWidth() {
        return mWidth;
    }

    public MrMesh getBoundMesh() {
        return mBoundMesh;
    }

    public void setMesh(MrMesh boundMesh) {
        mBoundMesh = boundMesh;
    }

    public MrShaderProgram getBoundShaderProgram() {
        return mBoundShaderProgram;
    }

    public void setShaderProgram(MrShaderProgram boundShaderProgram) {
        mBoundShaderProgram = boundShaderProgram;
    }

    public MrTexture getBoundTexture() {
        return mBoundTexture;
    }

    public void setTexture(MrTexture boundTexture) {
        mBoundTexture = boundTexture;
    }
}
