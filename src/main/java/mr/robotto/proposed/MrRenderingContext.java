/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;


import mr.robotto.proposed.aus.MrUniformGeneratorMap;
import mr.robotto.scenetree.MrSceneObjectsTree;

/**
 * Created by Aarón on 28/11/2014.
 */
public class MrRenderingContext {
    private MrSceneObjectsTree mObjectsTree;
    private MrUniformGeneratorMap mUniformGenerators;
    private int mWidth;
    private int mHeight;

    public MrRenderingContext(MrSceneObjectsTree objectsTree) {
        mObjectsTree = objectsTree;
        mUniformGenerators = new MrUniformGeneratorMap();
    }

    public MrSceneObjectsTree getObjectsTree() {
        return mObjectsTree;
    }

    public MrUniformGeneratorMap getUniformGenerators() {
        return mUniformGenerators;
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
