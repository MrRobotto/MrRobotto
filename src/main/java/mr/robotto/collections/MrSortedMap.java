/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.collections;

import java.util.HashMap;
import java.util.TreeSet;

import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.collections.core.MrOrderingFunction;

/**
 * Created by aaron on 10/02/2015.
 */
//TODO: You should provide methods to give SortedSet/Map interface
public class MrSortedMap<K, V> extends MrGroupedMap<K, Integer, V> {

    private HashMap<Integer, HashMap<K, V>> mGroupedElements;
    private TreeSet<Integer> mKeys;

    public MrSortedMap(MrMapFunction<K, V> mapFunction, MrOrderingFunction<V> orderingFunction) {
        super(mapFunction, orderingFunction);
        init();
        //mMapFunction = mapFunction;
    }

    private void init() {
        mGroupedElements = new HashMap<>();
        mKeys = new TreeSet<>();
    }

    /*public V first() {

    }

    public */

}
