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

import mr.robotto.collections.core.MrGroupingFunction;
import mr.robotto.collections.core.MrMapFunction;

/**
 * Created by aaron on 10/02/2015.
 */
public class MrGroupedMap<K, G, V> extends MrMapContainer<K, V> {

    private HashMap<G, MrMap<K, V>> mGroupedElements;
    private MrGroupingFunction<G, V> mGroupingFunction;

    public MrGroupedMap(MrMapFunction<K, V> mapFunction, MrGroupingFunction<G, V> groupingFunction) {
        super(mapFunction);
        mGroupingFunction = groupingFunction;
    }

    protected void init2() {
        super.init2();
        mGroupedElements = new HashMap<>();
    }

    @Override
    public boolean add(V v) {
        G group = mGroupingFunction.getGroupOf(v);
        K key = mMapFunction.getKeyOf(v);
        if (!mGroupedElements.containsKey(group)) {
            mGroupedElements.put(group, new MrMapContainer<K, V>(mMapFunction));
        }
        mGroupedElements.get(group).add(v);
        return mElements.put(key, v) != null;
    }

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
        K key = mMapFunction.getKeyOf(v);
        return removeByKey(key);
    }

    @Override
    public boolean removeByKey(K k) {
        V v = mElements.remove(k);
        if (v != null) {
            G group = mGroupingFunction.getGroupOf(v);
            mGroupedElements.get(group).removeByKey(k);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        mElements.clear();
        mGroupedElements.clear();
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
    public Iterator<V> iterator() {
        return mElements.values().iterator();
    }

    public MrMap<K, V> getByGroup(G group) {
        return mGroupedElements.get(group);
    }
}
