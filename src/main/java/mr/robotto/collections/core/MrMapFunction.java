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
 * Interface used for get the map key of the element V
 *
 * @param <K> Class of the key of element v
 * @param <V> Class of the element to be evaluated
 */
public interface MrMapFunction<K, V> {
    /**
     * Gets the key of the given object v
     *
     * @param v
     * @return the key of v
     */
    public K getKeyOf(V v);
}