/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.collections;

import java.util.Set;

/**
 * Map interface for MrRobotto Engine collections
 *
 * @param <K> key class
 * @param <V> data class
 */
public interface MrMap<K, V> extends Iterable<V> {

    /**
     * Adds an element to this map, if the key assigned to v it is already used
     * in the map the value will be override.
     *
     * @param v
     * @return true if the addition has been done, false otherwise
     */
    boolean add(V v);

    /**
     * Add all elements of the container to this map.
     *
     * @param container
     * @return true if the addition has been done, false otherwise.
     */
    boolean addAll(MrMap<K, V> container);

    /**
     * Removes the element v from this map if it is present.
     *
     * @param v
     * @return true if the deletion has been done, false otherwise.
     */
    boolean remove(V v);

    /**
     * Removes an element from this map using its key.
     *
     * @param k
     * @return true if the deletion has been done, false otherwise.
     */
    boolean removeByKey(K k);

    /**
     * Removes all of the elements from this map.
     */
    void clear();

    /**
     * Checks if the element v is present at this map.
     *
     * @param v
     * @return true if this map contains the element v, false otherwise.
     */
    boolean contains(V v);

    /**
     * Checks if this map is or not empty
     *
     * @return true if this map is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Counts the number of elements in this map
     *
     * @return the number of elements
     */
    int size();

    /**
     * Finds an element in this map given its key
     *
     * @param k element key
     * @return Returns the element if the key given have been found, null otherwise
     */
    V findByKey(K k);

    /**
     * Checks if the key k belongs to this map
     *
     * @param k key
     * @return true if the key k belongs to this map, false otherwise
     */
    boolean containsKey(K k);

    /**
     * Gets the set of keys in this map
     *
     * @return
     */
    Set<K> getKeys();
}
