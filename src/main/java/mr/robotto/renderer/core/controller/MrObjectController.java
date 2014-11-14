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
import mr.robotto.renderer.proposed.MrAction;
import mr.robotto.renderer.core.view.objectrenderers.MrObjectRender;

public class MrObjectController<T extends MrObjectData, R extends MrObjectRender<T>> {
    private T data;
    private R render;
    private Queue<MrAction<T>> actions;

    protected MrObjectController(T data, R render) {
        this.data = data;
        this.render = render;
        this.render.linkWith(data);
    }

    private void init() {
        actions = new LinkedList<MrAction<T>>();
    }

    public void initialize() {
        render.initialize();
    }

    public void render() {
        render.render();
    }

    public T getData() {
        return data;
    }

    public String getName() {
        return data.getName();
    }

    public R getRender() {
        return render;
    }

    public void setRender(R render) {
        this.render = render;
    }

    public void addAction(final MrAction<T> action) {
        actions.add(action);
    }

    public Collection<MrAction<T>> getActions() {
        return actions;
    }
}
