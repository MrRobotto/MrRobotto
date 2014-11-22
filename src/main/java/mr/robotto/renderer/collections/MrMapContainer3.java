/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.collections;

import java.util.HashMap;
import java.util.Iterator;

public class MrMapContainer3<K,V> implements MrContainer<V>, MrMap<K, V> {

    private HashMap<Integer, V> mElements;

    public MrMapContainer3() {
        init();
    }

    private void init() {
        mElements = new HashMap<Integer, V>();
    }

    @Override
    public boolean add(V v) {
        return mElements.put(v.hashCode(), v) != null;
    }

    @Override
    public void clear() {
        mElements.clear();
    }

    @Override
    public boolean contains(V v) {
        return mElements.containsKey(v.hashCode());
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
        return mElements.remove(v.hashCode()) != null;
    }

    @Override
    public int size() {
        return mElements.size();
    }

    @Override
    public boolean put(K k, V v) {
        return mElements.put(k.hashCode(), v) != null;
    }

    @Override
    public V find(K k) {
        return mElements.get(k.hashCode());
    }

    @Override
    public boolean containsKey(K k) {
        return mElements.containsKey(k.hashCode());
    }

    @Override
    public boolean removeByKey(K k) {
        return mElements.remove(k.hashCode()) != null;
    }
}
