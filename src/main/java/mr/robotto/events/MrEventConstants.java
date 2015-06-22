/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.events;

/**
 * Default events provided by MrRobotto 3D Engine
 */
public class MrEventConstants {
    /**
     * New frame rendered event
     */
    public static final String ON_TICK = "MrOnTick";
    /**
     * New touch event
     */
    public static final String ON_TOUCH = "MrOnTouch";

    /**
     * MotionEvent touch event argument name
     */
    public static final String ON_TOUCH_ARG_MOTIONEVENT = "MrMotionEvent";
}
