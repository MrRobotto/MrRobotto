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

import java.util.Set;


/**
 * This is the default class for event listening.
 * Note that it implements some of usual Android event listeners interfaces, however you should
 * not modify the received view on this method because this method will be executed outside the UIThread
 */
public class MrDefaultEventListener extends MrEventsListener {

    public static final String ON_TOUCH = "MrOnTouch";
    public static final String ON_TOUCH_MOTIONEVENT = "MrMotionEvent";

    public boolean onTouch(MotionEvent event) {
        System.out.println("Hola desde "+getAttachedObject().getName());
        return false;
    }

    @Override
    protected void proccessEvent(String eventName, MrBundle eventBundle) {
        super.proccessEvent(eventName, eventBundle);
        if (eventName.equals(ON_TOUCH)) {
            onTouch(eventBundle.getMotionEvent(ON_TOUCH_MOTIONEVENT));
        }
    }

    @Override
    protected void registerEvents(Set<String> events) {
        events.add(ON_TOUCH);
    }
}
