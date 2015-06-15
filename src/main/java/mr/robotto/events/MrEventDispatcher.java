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

import mr.robotto.MrRobotto;
import mr.robotto.core.controller.MrObjectController;

/**
 * Created by aaron on 14/06/2015.
 */
public class MrEventDispatcher implements View.OnTouchListener {
    public static final String ON_TOUCH = "onTouch";

    private HashMap<String, ArrayList<MrObjectController>> mObjects;

    public MrEventDispatcher() {
        mObjects = new HashMap<>();
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        final ArrayList<MrObjectController> objs = mObjects.get(ON_TOUCH);
        if (objs == null || objs.size() == 0) {
            return false;
        }
        MrRobotto.getInstance().queueEvent(new Runnable() {
            @Override
            public void run() {
                for (MrObjectController obj : objs) {
                    ((View.OnTouchListener)obj.getEventsListener()).onTouch(v, event);
                }
            }
        });
        return true;
    }
}
