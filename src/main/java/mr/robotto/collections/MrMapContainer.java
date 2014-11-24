/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.collections;

import java.util.HashMap;
import java.util.Iterator;

public class MrMapContainer<K,V> implements MrContainer<V>, MrMap<K, V> {

    private HashMap<K, V> mElements;

    private MrMapFunction<K,V> mMapFunction;

    public MrMapContainer(MrMapFunction<K, V> mapFunction) {
        init();
        mMapFunction = mapFunction;
    }

    private void init() {
        mElements = new HashMap<K, V>();
    }

    @Override
    public boolean add(V v) {
        return mElements.put(mMapFunction.getIdOf(v), v) != null;
    }

    @Override
    public void clear() {
        mElements.clear();
    }

    @Override
    public boolean contains(V v) {
        return mElements.containsKey(mMapFunction.getIdOf(v));
    }

    @Override
    public boolean isEmpty() {
        return mElements.isEmpty();
    }

    @Override
    public Iterator<V> iterator() {
        return mElements.values().iterator();
    }

    @Override
    public boolean remove(V v) {
        return removeByKey(mMapFunction.getIdOf(v));
    }

    @Override
    public int size() {
        return mElements.size();
    }

    @Override
    public boolean put(K k, V v) {
        return mElements.put(k, v) != null;
    }

    @Override
    public V find(K k) {
        return mElements.get(k);
    }

    @Override
    public boolean containsKey(K k) {
        return mElements.containsKey(k);
    }

    @Override
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
