/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.events;

import java.util.HashSet;
import java.util.Set;

import mr.robotto.core.data.MrObjectData;

/**
 * Created by aaron on 14/06/2015.
 */
public abstract class MrEventsListener {

    private MrObjectData mObject;
    private Set<String> mEvents;

    public MrEventsListener() {
        mEvents = new HashSet<>();
        registerEvents(mEvents);
    }

    public void setAttachedObject(MrObjectData object) {
        mObject = object;
    }

    public MrObjectData getAttachedObject() {
        return mObject;
    }

    public Set<String> getRegisteredEvents() {
        return mEvents;
    }

    protected abstract void registerEvents(Set<String> events);
}
