/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.collections.core;

import java.util.Set;

/**
 * Created by aaron on 10/02/2015.
 */
public interface MrMap<K, V> extends Iterable<V> {
    boolean add(V v);

    //TODO: Check addition when override
    boolean addAll(MrMap<K, V> container);

    boolean remove(V v);

    boolean removeByKey(K k);

    void clear();

    boolean contains(V v);

    boolean isEmpty();

    int size();

    V findByKey(K k);

    boolean containsKey(K k);

    Set<K> getKeys();
}
