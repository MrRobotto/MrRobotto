/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.events;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import mr.robotto.MrRobotto;
import mr.robotto.core.controller.MrObjectController;

/**
 * Created by aaron on 14/06/2015.
 */
public class MrEventDispatcher implements View.OnTouchListener {

    private HashMap<String, ArrayList<MrObjectController>> mObjects;

    public MrEventDispatcher() {
        mObjects = new HashMap<>();
    }

    public void addObject(MrObjectController obj) {
        Set<String> events = obj.getEventsListener().getRegisteredEvents();
        for (String ev : events) {
            if (mObjects.containsKey(ev)) {
                mObjects.get(ev).add(obj);
            } else {
                mObjects.put(ev, new ArrayList<MrObjectController>());
                mObjects.get(ev).add(obj);
            }
        }
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        final ArrayList<MrObjectController> objs = mObjects.get(MrDefaultEventListener.ON_TOUCH);
        if (objs == null || objs.size() == 0) {
            return false;
        }
        MrRobotto.getInstance().queueEvent(new Runnable() {
            @Override
            public void run() {
                for (MrObjectController obj : objs) {
                    MrBundle bundle = new MrBundle();
                    bundle.putMotionEvent(MrDefaultEventListener.ON_TOUCH_MOTIONEVENT, event);
                    obj.getEventsListener().proccessEvent(MrDefaultEventListener.ON_TOUCH, bundle);
                }
            }
        });
        return true;
    }
}
