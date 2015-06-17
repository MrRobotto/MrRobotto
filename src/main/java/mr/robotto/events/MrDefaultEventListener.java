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

import java.util.Set;


/**
 * This is the default class for event listening.
 * Note that it implements some of usual Android event listeners interfaces, however you should
 * not modify the received view on this method because this method will be executed outside the UIThread
 */
public class MrDefaultEventListener extends MrEventsListener {

    public static final String ON_TICK = "MrOnTick";

    public static final String ON_TOUCH = "MrOnTouch";
    public static final String ON_TOUCH_MOTIONEVENT = "MrMotionEvent";

    private float alpha = 0.1f;

    public boolean onTouch(MotionEvent event) {
        return false;
    }

    public void onTick() {
        if (getAttachedObject().getName().equals("link"))
            getAttachedObject().rotate(0.2f, 0, 0, 1);
    }

    @Override
    protected void processEvent(String eventName, MrBundle eventBundle) {
        super.processEvent(eventName, eventBundle);
        if (eventName.equals(ON_TOUCH)) {
            onTouch(eventBundle.getMotionEvent(ON_TOUCH_MOTIONEVENT));
        }
        if (eventName.equals(ON_TICK)) {
            onTick();
        }
    }

    @Override
    protected void registerEvents(Set<String> events) {
        events.add(ON_TOUCH);
        events.add(ON_TICK);
    }
}
