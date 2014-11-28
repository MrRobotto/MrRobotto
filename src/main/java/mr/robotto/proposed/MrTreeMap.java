/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed;


import mr.robotto.collections.MrMapNode;

/**
 * Created by Aarón on 26/11/2014.
 */
public interface MrTreeMap<K,V> extends Iterable<V> {

    public int size();

    public boolean putNode(K key, MrMapNode<K,V> node);

    public boolean clear();

    public boolean removeNode(K key);

    public MrMapNode<K,V> getRoot();

    public boolean isEmpty();

    public boolean containsKey(K key);

    public MrMapNode<K,V> findNode(K key);

    public V findByKey(K key);
}
