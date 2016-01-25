/*
 *  MrRobotto 3D Engine
 *  Copyright (c) 2016, Aarón Negrín, All rights reserved.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.engine.events;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import mr.robotto.engine.core.controller.MrObjectController;
import mr.robotto.sceneobjects.MrObject;

/**
 * Class for processing events linked to an object
 */
public abstract class MrEventsListener {

    private final MrBundle mAuxiliarBundle = new MrBundle();
    private MrObjectController mObjectController;
    private Set<String> mEvents;
    private Queue<String> mEventNamesQueue;
    private Queue<MrBundle> mEventBundlesQueue;

    /**
     * Creates a new Event Listener
     */
    public MrEventsListener() {
        mEvents = new HashSet<>();
        mEventNamesQueue = new ArrayDeque<>();
        mEventBundlesQueue = new ArrayDeque<>();
        registerEvents(mEvents);
    }

    /**
     * Gets the object controller which this event listener is attached to
     * @return
     */
    public MrObjectController getObjectController() {
        return mObjectController;
    }

    /**
     * Sets the object controller
     * @param controller
     */
    public void setObjectController(MrObjectController controller) {
        mObjectController = controller;
    }

    /**
     * Gets the object which this event listener is attached to
     * @return
     */
    public MrObject getAttachedObject() {
        return mObjectController.getAttachedObject();
    }

    /**
     * Checks if the given event is registered to be processed
     * @param evName
     * @return
     */
    public boolean isEventRegistered(String evName) {
        return mEvents.contains(evName);
    }

    /**
     * Gets all registered events
     * @return
     */
    public Set<String> getRegisteredEvents() {
        return mEvents;
    }

    /**
     * Register multiple events at once
     * @param events
     */
    protected void registerEvents(Set<String> events) {

    }

    /**
     * Registers a certain event
     * @param eventName
     */
    public void registerEvent(String eventName) {
        mEvents.add(eventName);
    }

    /**
     * Unregisters a certain event
     * @param eventName
     */
    public void unregisterEvent(String eventName) {
        mEvents.remove(eventName);
    }

    /**
     * Queues a new event to be processed on the OpenGL Thread
     * This method should be always called from a {@link MrEventDispatcher} instance
     * @param eventName
     * @param eventBundle
     */
    public final void queueEvent(String eventName, MrBundle eventBundle) {
        if (eventName == null) {
            return;
        }
        if (mEvents.contains(eventName)) {
            mEventNamesQueue.add(eventName);
            if (eventBundle == null) {
                mEventBundlesQueue.add(mAuxiliarBundle);
            } else {
                mEventBundlesQueue.add(eventBundle);
            }
        }
    }

    /**
     * Process a certain event
     * @param eventName event name received
     * @param eventBundle bundle containing event data
     */
    protected void processEvent(String eventName, MrBundle eventBundle) {

    }


    /**
     * Request to process all queued events
     * This method should not be called by user
     */
    public final void updateEvents() {
        while (!mEventNamesQueue.isEmpty()) {
            String evName = mEventNamesQueue.poll();
            MrBundle evBundle = mEventBundlesQueue.poll();
            if (mEvents.contains(evName)) {
                processEvent(evName, evBundle);
            }
        }
    }

    @Override
    public String toString() {
        String s =
                "MrEventsListener{" +
                "Object=" + mObjectController.getName() +
                ", RegisteredEvents=[";
        for (String ev : mEvents) {
            s += ev +",";
        }
        s += "]}";
        return s;
    }
}
