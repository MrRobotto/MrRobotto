/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.scenetree;

import java.util.List;

import mr.robotto.core.MrObject;
import mr.robotto.core.MrSceneObjectType;
import mr.robotto.renderer.MrRenderingContext;

/**
 * Created by aaron on 30/01/2015.
 */
public class MrSceneTreeController {
    private MrSceneTreeRender mRender;
    private MrSceneTree mData;
    private MrRenderingContext mRenderingContext;

    public MrSceneTreeController(MrSceneTree tree, MrSceneTreeRender render) {
        mRender = render;
        mData = tree;
        mRenderingContext = new MrRenderingContext(mData);
    }

    public MrSceneTree getSceneTree() {
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

    public List<MrObject> getByType(MrSceneObjectType type) {
        return mData.getByType(type);
    }

    public MrObject findByKey(String key) {
        return mData.findByKey(key);
    }
}
