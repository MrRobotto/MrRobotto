/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer;


import mr.robotto.components.data.uniformkey.MrUniformKeyMap;
import mr.robotto.scenetree.MrSceneTree;

/**
 * Created by Aarón on 28/11/2014.
 */
public class MrRenderingContext {
    private MrSceneTree mObjectsTree;
    private MrUniformKeyMap mUniforms;
    private int mWidth;
    private int mHeight;

    public MrRenderingContext(MrSceneTree objectsTree) {
        mObjectsTree = objectsTree;
        mUniforms = new MrUniformKeyMap();
    }

    public MrSceneTree getObjectsTree() {
        return mObjectsTree;
    }


    public MrUniformKeyMap getUniforms() {
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
