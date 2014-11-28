/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.collections;

import java.util.ArrayList;
import java.util.Iterator;

import mr.robotto.collections.core.MrContainer;

/**
 * Created by Aarón on 28/11/2014.
 */
public class MrListCointainer<V> implements MrContainer<V> {

    private ArrayList<V> mList;

    public MrListCointainer() {
        mList = new ArrayList<V>();
    }

    @Override
    public boolean add(V v) {
        return mList.add(v);
    }

    @Override
    public void clear() {
        mList.clear();
    }

    @Override
    public boolean contains(V v) {
        return mList.contains(v);
    }

    @Override
    public boolean isEmpty() {
        return mList.isEmpty();
    }

    @Override
    public boolean remove(V v) {
        return mList.remove(v);
    }

    @Override
    public int size() {
        return mList.size();
    }

    @Override
    public Iterator<V> iterator() {
        return mList.iterator();
    }
}
