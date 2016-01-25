/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.events;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import mr.robotto.MrEngine;
import mr.robotto.engine.core.controller.MrObjectController;
import mr.robotto.engine.scenetree.MrSceneTreeController;

/**
 * Created by aaron on 14/06/2015.
 */
public class MrEventDispatcher implements View.OnTouchListener {

    private HashMap<String, ArrayList<MrObjectController>> mObjects;
    private MrSceneTreeController mController;
    private MrEngine mRobottoEngine;

    public MrEventDispatcher() {
        mObjects = new HashMap<>();
    }

    public void initializeEventDispatcher(MrEngine robottoEngine, MrSceneTreeController controller) {
        mController = controller;
        mRobottoEngine = robottoEngine;
        for (MrObjectController obj : controller.getSceneTreeData()) {
            addObject(obj);
        }
    }

    private void addObject(MrObjectController obj) {
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
        final ArrayList<MrObjectController> objs = mObjects.get(MrEventConstants.ON_TOUCH);
        if (objs == null || objs.size() == 0) {
            return false;
        }
        mRobottoEngine.queueEvent(new Runnable() {
            @Override
            public void run() {
                for (MrObjectController obj : objs) {
                    MrBundle bundle = new MrBundle();
                    bundle.putMotionEvent(MrEventConstants.ON_TOUCH_ARG_MOTIONEVENT, event);
                    obj.getEventsListener().queueEvent(MrEventConstants.ON_TOUCH, bundle);
                }
            }
        });
        return true;
    }
}
