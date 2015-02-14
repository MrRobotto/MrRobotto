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
import java.util.Set;

import mr.robotto.collections.core.MrMapFunction;

//TODO: Rename this to MrMap
public class MrMapContainer<K, V> implements MrMap<K, V> {

    private HashMap<K, V> mElements;
    private MrMapFunction<K, V> mMapFunction;

    public MrMapContainer(MrMapFunction<K, V> mapFunction) {
        init();
        mMapFunction = mapFunction;
    }

    //TODO: Change method name and encapsulation
    private void init() {
        mElements = new HashMap<K, V>();
    }

    @Override
    public boolean add(V v) {
        return mElements.put(mMapFunction.getKeyOf(v), v) != null;
    }

    //TODO: Check addition when override
    @Override
    public boolean addAll(MrMap<K, V> container) {
        boolean added = true;
        for (V data : container) {
            added &= add(data);
        }
        return added;
    }

    @Override
    public boolean remove(V v) {
        return removeByKey(mMapFunction.getKeyOf(v));
    }

    @Override
    public boolean removeByKey(K k) {
        return mElements.remove(k) != null;
    }

    @Override
    public void clear() {
        mElements.clear();
    }

    @Override
    public boolean contains(V v) {
        return mElements.containsKey(mMapFunction.getKeyOf(v));
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
    public int size() {
        return mElements.size();
    }

    @Override
    public V findByKey(K k) {
        return mElements.get(k);
    }

    @Override
    public boolean containsKey(K k) {
        return mElements.containsKey(k);
    }

    @Override
    public Set<K> getKeys() {
        return mElements.keySet();
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
