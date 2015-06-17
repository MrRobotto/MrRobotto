/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.events;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import mr.robotto.core.MrObject;
import mr.robotto.core.controller.MrObjectController;

/**
 * Created by aaron on 14/06/2015.
 */
public abstract class MrEventsListener {

    private final MrBundle mAuxiliarBundle = new MrBundle();
    private MrObjectController mObjectController;
    private Set<String> mEvents;
    private Queue<String> mEventNamesQueue;
    private Queue<MrBundle> mEventBundlesQueue;

    public MrEventsListener() {
        mEvents = new HashSet<>();
        mEventNamesQueue = new ArrayDeque<>();
        mEventBundlesQueue = new ArrayDeque<>();
        registerEvents(mEvents);
    }

    public MrObjectController getObjectController() {
        return mObjectController;
    }

    public void setObjectController(MrObjectController controller) {
        mObjectController = controller;
    }

    public MrObject getAttachedObject() {
        return mObjectController.getAttachedObject();
    }

    public boolean isEventRegistered(String evName) {
        return mEvents.contains(evName);
    }

    public Set<String> getRegisteredEvents() {
        return mEvents;
    }

    protected abstract void registerEvents(Set<String> events);

    public void queueEvent(String eventName, MrBundle eventBundle) {
        if (eventName == null) {
            return;
        }
        if (isEventRegistered(eventName)) {
            mEventNamesQueue.add(eventName);
            if (eventBundle == null) {
                mEventBundlesQueue.add(mAuxiliarBundle);
            } else {
                mEventBundlesQueue.add(eventBundle);
            }
        }
    }

    protected void processEvent(String eventName, MrBundle eventBundle) {

    }

    public void updateEvents() {
        while (!mEventNamesQueue.isEmpty()) {
            String evName = mEventNamesQueue.poll();
            MrBundle evBundle = mEventBundlesQueue.poll();
            processEvent(evName, evBundle);
        }
    }
}
