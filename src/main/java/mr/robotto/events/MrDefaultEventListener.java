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

    public boolean onTouch(MotionEvent event) {
        return false;
    }

    public void onTick() {
    }

    @Override
    protected void processEvent(String eventName, MrBundle eventBundle) {
        super.processEvent(eventName, eventBundle);
        if (eventName.equals(MrEventConstants.ON_TOUCH)) {
            onTouch(eventBundle.getMotionEvent(MrEventConstants.ON_TOUCH_ARG_MOTIONEVENT));
        }
        if (eventName.equals(MrEventConstants.ON_TICK)) {
            onTick();
        }
    }

    @Override
    protected void registerEvents(Set<String> events) {

    }
}
