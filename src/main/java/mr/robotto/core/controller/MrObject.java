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
import mr.robotto.core.rendereable.objectrenderers.MrObjectRender;
import mr.robotto.proposed.MrAction;

public abstract class MrObject {
    private MrObjectData mData;
    private MrObjectRender mRender;
    private Queue<MrAction<MrObjectData>> mActions;

    protected MrObject(MrObjectData data, MrObjectRender render) {
        mData = data;
        mRender = render;
        mRender.linkWith(data);
    }

    private void init() {
        mActions = new LinkedList<MrAction<MrObjectData>>();
    }

    public void initialize() {
        mRender.initialize();
    }

    public boolean isInitialized() {
        return mRender.isInitialized();
    }

    public void render() {
        mRender.render();
    }

    public MrObjectData getData() {
        return mData;
    }

    public String getName() {
        return mData.getName();
    }

    public MrObjectRender getRender() {
        return mRender;
    }

    public void setRender(MrObjectRender render) {
        this.mRender = render;
    }

    public MrSceneObjectType getSceneObjectType() {
        return mData.getSceneObjectType();
    }

    public void addAction(final MrAction<MrObjectData> action) {
        mActions.add(action);
    }

    public Collection<MrAction<MrObjectData>> getActions() {
        return mActions;
    }
}
