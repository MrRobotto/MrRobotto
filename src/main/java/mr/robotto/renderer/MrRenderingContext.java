/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer;


import java.util.HashMap;

import mr.robotto.components.data.uniformkey.MrUniformKey;
import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.scenetree.MrSceneTree;
import mr.robotto.scenetree.MrSceneTreeData;

/**
 * Created by Aarón on 28/11/2014.
 */
public class MrRenderingContext {
    private MrSceneTreeData mObjectsTree;
    private HashMap<String,MrUniformKey> mUniforms;
    private int mWidth;
    private int mHeight;

    public MrRenderingContext(MrSceneTreeData objectsTree) {
        mObjectsTree = objectsTree;
        //mUniforms = new MrUniformKeyMap();
        mUniforms = new HashMap<>();
    }

    public MrSceneTreeData getObjectsTree() {
        return mObjectsTree;
    }


    public HashMap<String, MrUniformKey> getUniforms() {
        return mUniforms;
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
}
