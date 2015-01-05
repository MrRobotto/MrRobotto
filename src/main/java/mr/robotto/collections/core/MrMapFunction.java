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
 * Created by Aarón on 23/11/2014.
 */
public interface MrMapFunction<K,V> {
    public K getKeyOf(V v);
}