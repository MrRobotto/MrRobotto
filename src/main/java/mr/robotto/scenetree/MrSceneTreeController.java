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

import mr.robotto.core.MrSceneObjectType;
import mr.robotto.core.controller.MrObjectController;
import mr.robotto.events.MrEventDispatcher;
import mr.robotto.renderer.MrRenderingContext;

/**
 * Created by aaron on 30/01/2015.
 */
public class MrSceneTreeController {
    private MrSceneTreeRender mRender;
    private MrSceneTreeData mData;
    private MrRenderingContext mRenderingContext;
    private MrEventDispatcher mEventDispatcher;

    public MrSceneTreeController(MrSceneTreeData tree, MrSceneTreeRender render) {
        mRender = render;
        mData = tree;
        mRenderingContext = new MrRenderingContext(mData);
        mEventDispatcher = new MrEventDispatcher();
    }

    public MrSceneTreeData getSceneTreeData() {
        return mData;
    }


    public MrRenderingContext getRenderingContext() {
        return mRenderingContext;
    }

    public MrEventDispatcher getEventDispatcher() {
        return mEventDispatcher;
    }

    public void setEventDispatcher(MrEventDispatcher eventDispatcher) {
        mEventDispatcher = eventDispatcher;
    }

    public void initializeRender() {
        mRender.initializeRender(mData, mRenderingContext);
        for (MrObjectController obj : mData) {

        }
    }

    public void initializeSizeDependant(int widthScreen, int heightScreen) {
        mRender.initializeSizeDependant(widthScreen, heightScreen);
    }

    public void render() {
        mRender.render();
    }

    public List<MrObjectController> getByType(MrSceneObjectType type) {
        return mData.getByType(type);
    }

    public MrObjectController findByKey(String key) {
        return mData.findByKey(key);
    }
}
