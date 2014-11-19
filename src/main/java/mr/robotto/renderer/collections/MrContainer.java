/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.collections;

import java.util.Iterator;

public interface MrContainer<V> extends Iterable<V> {

    public boolean add(V v);

    public void clear();

    public boolean contains(V v);

    public boolean isEmpty();

    public boolean remove(V v);

    public int size();

    @Override
    public Iterator<V> iterator();
}
