/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.collections.core;

public interface MrMap<K, V> {
    public boolean removeByKey(K k);

    public boolean containsKey(K k);

    public boolean put(K k, V v);

    public V findByKey(K k);
}
