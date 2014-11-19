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

import mr.robotto.renderer.proposed.MrIdentificable;

public class MrMapContainer<K,V extends MrIdentificable<K>> implements MrContainer<V>, MrMap<K, V> {

    private HashMap<K, V> mElements;

    public MrMapContainer() {
        init();
    }

    private void init() {
        mElements = new HashMap<K, V>();
    }

    @Override
    public boolean add(V v) {
        return mElements.put(v.getElementId(), v) != null;
    }

    @Override
    public void clear() {
        mElements.clear();
    }

    @Override
    public boolean contains(V v) {
        return mElements.containsKey(v.getElementId());
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
        return removeByKey(v.getElementId());
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
            s += v.getElementId().toString();
        }
        return s;
    }
}
