/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.proposed.containersold;

import java.util.Collection;
import java.util.Iterator;

import mr.robotto.renderer.proposed.MrIdentificable;
import mr.robotto.renderer.collections.MrMapContainer;

public class MrMapNode3<K, V extends MrIdentificable<K>> extends MrNode2<V> implements MrIdentificable<K> {

    private MrMapContainer<K, MrMapNode3<K,V>> mMapContainer;

    public MrMapNode3(MrNode2<V> parent, V data) {
        super(parent, data);
        init();
    }

    private void init() {
        mMapContainer = new MrMapContainer<K, MrMapNode3<K, V>>();
    }

    @Override
    public K getElementId() {
        return getData().getElementId();
    }

    public MrMapNode3<K, V> find(K k) {
        return mMapContainer.find(k);
    }

    public MrMapNode3<K,V> getParent() {
        return (MrMapNode3<K, V>) super.getParent();
    }

    //TODO
    @Override
    public Collection<MrNode2<V>> getChildren() {
        return null;//super.getChildren();
    }

    //TODO
    @Override
    public Iterator<MrNode2<V>> iterator() {
        return super.iterator();
    }

    public boolean addChild(MrMapNode3<K,V> node) {
        for (MrNode2<V> n : node) {
            mMapContainer.add((MrMapNode3<K, V>) n);
        }
        return super.addChild(node);
    }

    public boolean removeChild(MrMapNode3<K,V> node) {
        for (MrNode2<V> n : node) {
            mMapContainer.removeByKey(((MrMapNode3<K, V>)n).getElementId());
        }
        return super.removeChild(node);
    }

    @Override
    public void clearChildren() {
        for (MrNode2<V> n : this) {
            mMapContainer.removeByKey(((MrMapNode3<K, V>)n).getElementId());
        }
        super.clearChildren();
    }

    @Override
    public void clearParent() {
        super.clearParent();
    }
}
