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
import java.util.Iterator;

import mr.robotto.collections.core.MrMapFunction;

public class MrMapContainer<K, V> implements Iterable<V> {

    private HashMap<K, V> mElements;

    private MrMapFunction<K, V> mMapFunction;

    public MrMapContainer(MrMapFunction<K, V> mapFunction) {
        init();
        mMapFunction = mapFunction;
    }

    private void init() {
        mElements = new HashMap<K, V>();
    }

    public boolean add(V v) {
        return mElements.put(mMapFunction.getKeyOf(v), v) != null;
    }

    public void clear() {
        mElements.clear();
    }

    public boolean contains(V v) {
        return mElements.containsKey(mMapFunction.getKeyOf(v));
    }

    public boolean isEmpty() {
        return mElements.isEmpty();
    }

    @Override
    public Iterator<V> iterator() {
        return mElements.values().iterator();
    }

    public boolean remove(V v) {
        return removeByKey(mMapFunction.getKeyOf(v));
    }

    public int size() {
        return mElements.size();
    }

    public boolean put(K k, V v) {
        return mElements.put(k, v) != null;
    }

    public V findByKey(K k) {
        return mElements.get(k);
    }

    public boolean containsKey(K k) {
        return mElements.containsKey(k);
    }

    public boolean removeByKey(K k) {
        return mElements.remove(k) != null;
    }

    @Override
    public String toString() {
        String s = "";
        for (V v : mElements.values()) {
            s += v.toString();
        }
        return s;
    }
}
