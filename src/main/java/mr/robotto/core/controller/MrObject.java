/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.core.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import mr.robotto.core.data.MrObjectData;
import mr.robotto.core.data.resources.commons.MrSceneObjectType;
import mr.robotto.core.rendereable.MrObjectRender;
import mr.robotto.proposed.MrAction;
import mr.robotto.proposed.MrRenderingContext;

public abstract class MrObject<D extends MrObjectData, R extends MrObjectRender<D>> {
    private D mData;
    private R mRender;
    private MrRenderingContext mContext;
    private Queue<MrAction<D>> mActions;

    protected MrObject(D data, R render) {
        mData = data;
        mRender = render;
        mRender.linkWith(data);
    }

    private void init() {
        mActions = new LinkedList<MrAction<D>>();
    }

    public void initialize() {
        mRender.initialize();
    }

    public boolean isInitialized() {
        return mRender.isInitialized();
    }

    public MrRenderingContext getRenderingContext() {
        return mContext;
    }

    public void setRenderingContext(MrRenderingContext context) {
        mContext = context;
    }

    public void render() {
        mRender.render();
    }

    public D getData() {
        return mData;
    }

    public String getName() {
        return mData.getName();
    }

    public R getRender() {
        return mRender;
    }

    public void setRender(R render) {
        this.mRender = render;
    }

    public MrSceneObjectType getSceneObjectType() {
        return mData.getSceneObjectType();
    }

    public void addAction(final MrAction<D> action) {
        mActions.add(action);
    }

    public Collection<MrAction<D>> getActions() {
        return mActions;
    }
}
