/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.core.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import mr.robotto.renderer.core.data.object.MrObjectData;
import mr.robotto.renderer.core.rendereable.objectrenderers.MrObjectRender;
import mr.robotto.renderer.proposed.MrAction;
import mr.robotto.renderer.proposed.MrIdentificable;

public class MrObject<T extends MrObjectData, R extends MrObjectRender> implements MrIdentificable<String> {
    private T mData;
    private R mRender;
    private Queue<MrAction<T>> mActions;

    protected MrObject(T data, R render) {
        mData = data;
        mRender = render;
        mRender.linkWith(data);
    }

    private void init() {
        mActions = new LinkedList<MrAction<T>>();
    }

    public void initialize() {
        mRender.initialize();
    }

    public void render() {
        mRender.render();
    }

    public T getData() {
        return mData;
    }

    @Override
    public String getElementId() {
        return getName();
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

    public void addAction(final MrAction<T> action) {
        mActions.add(action);
    }

    public Collection<MrAction<T>> getActions() {
        return mActions;
    }
}
