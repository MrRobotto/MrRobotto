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
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import mr.robotto.collections.core.MrMap;
import mr.robotto.collections.core.MrMapFunction;
import mr.robotto.collections.core.MrOrderingFunction;

/**
 * Created by aaron on 15/02/2015.
 */
public class MrSortedMap<K, V> implements MrMap<K, V> {

    private HashMap<K, V> mElements;
    private MrMapFunction<K, V> mMapFunction;
    //TODO: Remove one of these elements
    private SortedMap<Integer, MrMap<K, V>> mGroupedElements;
    private SortedSet<Integer> mKeys;
    private MrOrderingFunction<V> mOrderingFunction;

    public MrSortedMap(MrMapFunction<K, V> mapFunction, MrOrderingFunction<V> orderingFunction) {
        init();
        mMapFunction = mapFunction;
        mOrderingFunction = orderingFunction;
    }

    private void init() {
        mElements = new HashMap<>();
        mGroupedElements = new TreeMap<>();
        mKeys = new TreeSet<>();
    }

    @Override
    public boolean add(V v) {
        int group = mOrderingFunction.getGroupOf(v);
        K key = mMapFunction.getKeyOf(v);
        if (!mGroupedElements.containsKey(group)) {
            mGroupedElements.put(group, new MrHashMap<K, V>(mMapFunction));
            mKeys.add(group);
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
            int group = mOrderingFunction.getGroupOf(v);
            mGroupedElements.get(group).removeByKey(k);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        mElements.clear();
        mGroupedElements.clear();
        mKeys.clear();
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
    public Set<K> getKeys() {
        return mElements.keySet();
    }

    @Override
    public Iterator<V> iterator() {
        return new MrSortedIterator();
    }

    public Set<Integer> getGroupKeys() {
        return mGroupedElements.keySet();
    }

    public MrMap<K, V> getByGroup(int group) {
        return mGroupedElements.get(group);
    }

    public boolean containsGroup(int group) {
        return mKeys.contains(group);
    }

    private class MrSortedIterator implements Iterator<V> {

        private int mCurrentKey;
        private Iterator<Integer> mKeyIterator;
        private Iterator<V> mCurrentElementIterator;

        private MrSortedIterator() {
            mCurrentKey = Integer.MIN_VALUE;
            mKeyIterator = mKeys.iterator();
            if (mKeyIterator.hasNext())
                mCurrentKey = mKeyIterator.next();
            if (mCurrentKey != Integer.MIN_VALUE)
                mCurrentElementIterator = mGroupedElements.get(mCurrentKey).iterator();
        }

        @Override
        public boolean hasNext() {
            return (mKeyIterator != null && mCurrentElementIterator != null) && (mKeyIterator.hasNext() || mCurrentElementIterator.hasNext());
        }

        @Override
        public V next() {
            if (mCurrentElementIterator.hasNext()) {
                return mCurrentElementIterator.next();
            } else if (mKeyIterator.hasNext()) {
                mCurrentKey = mKeyIterator.next();
                mCurrentElementIterator = mGroupedElements.get(mCurrentKey).iterator();
                if (mCurrentElementIterator.hasNext()) {
                    return mCurrentElementIterator.next();
                }
            }
            return null;
        }

        @Override
        public void remove() {

        }
    }
}
