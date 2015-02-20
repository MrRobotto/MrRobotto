/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.collections.core;

/**
 * Interface used for get the group G of the element V
 *
 * @param <G> Class of the group of element v
 * @param <V> Class of the element to be evaluated
 */
public interface MrGroupingFunction<G, V> {
    /**
     * Gets the group of v
     *
     * @param v element to be evaluated
     * @return the group of v
     */
    public G getGroupOf(V v);
}
