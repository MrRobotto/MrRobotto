/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.context;

import mr.robotto.proposed.MrRenderingContext;

/**
 * Created by aaron on 30/01/2015.
 */
public class MrSceneObjectsTreeController {
    private MrSceneObjectsTreeRender mRender;
    private MrSceneObjectsTree mData;
    private MrRenderingContext mRenderingContext;

    public MrSceneObjectsTreeController(MrSceneObjectsTree tree, MrSceneObjectsTreeRender render) {
        mRender = render;
        mData = tree;
        mRenderingContext = new MrRenderingContext(mData);
    }

    /*public void initializeRender(MrRenderingContext context) {
        mRenderingContext = context;
        mRender.initializeRender(mObjectsTree, context);
    }*/

    public MrSceneObjectsTree getData() {
        return mData;
    }


    public MrRenderingContext getRenderingContext() {
        return mRenderingContext;
    }


    public void initializeRender() {
        mRender.initializeRender(mData, mRenderingContext);
    }

    public void initializeSizeDependant(int widthScreen, int heightScreen) {
        mRender.initializeSizeDependant(widthScreen, heightScreen);
    }

    public void render() {
        mRender.render();
    }


}
