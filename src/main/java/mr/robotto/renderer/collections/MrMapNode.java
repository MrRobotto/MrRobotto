/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import mr.robotto.renderer.proposed.MrIdentificable;

/**
 * Created by Aarón on 17/11/2014.
 */
public class MrMapNode<K, V extends MrIdentificable<K>> implements MrIdentificable<K>, MrNode<V>, MrMap<K,MrMapNode<K,V>>, Iterable<MrMapNode<K,V>> {

    private MrMapNode<K,V> mParent;
    private V mData;
    private HashMap<K, MrMapNode<K,V>> mChildren;
    private HashMap<K, MrMapNode<K,V>> mTree;
    private int mDepth;

    public MrMapNode(MrMapNode<K,V> parent, V data) {
        init();
        if (parent != null) {
            parent.addChild(this);
        }
        mData = data;
    }

    private void init() {
        mChildren = new HashMap<K, MrMapNode<K, V>>();
        mTree = new HashMap<K, MrMapNode<K, V>>();
        mParent = null;
        mDepth = 0;
    }

    private void setParent(MrMapNode<K,V> parent) {
        mParent = parent;
        setDepth();
    }

    private void setDepth() {
        if (mParent != null) {
            mDepth = mParent.getDepth() + 1;
        } else {
            mDepth = 0;
        }
    }

    @Override
    public K getElementId() {
        return mData.getElementId();
    }

    @Override
    public V getData() {
        return mData;
    }

    @Override
    public int getDepth() {
        return mDepth;
    }

    @Override
    public boolean isLeaf() {
        return mChildren.isEmpty();
    }

    @Override
    public boolean hasParent() {
        return mParent != null;
    }

    @Override
    public MrMapNode<K,V> getParent() {
        return mParent;
    }

    @Override
    public Collection<MrMapNode<K,V>> getChildren() {
        return mChildren.values();
    }

    @Override
    public boolean addChild(MrNode<V> node) {
        MrMapNode<K,V> n = (MrMapNode<K, V>) node;
        if (n.hasParent()) {
            n.getParent().removeChild(n);
        }
        n.setParent(this);
        for (MrNode<V> m : n) {
            MrMapNode<K,V> aux = (MrMapNode<K, V>) m;
            aux.setDepth();
            mTree.put(aux.getElementId(), aux);
        }
        return mChildren.put(n.getElementId(), n) != null;
    }

    @Override
    public boolean removeChild(MrNode<V> node) {
        MrMapNode<K,V> n = (MrMapNode<K,V>) node;
        if (mChildren.remove(n.getElementId()) != null ) {
            n.setParent(null);
            for (MrNode<V> m : n) {
                MrMapNode<K,V> aux = (MrMapNode<K, V>) m;
                aux.setDepth();
                mTree.remove(aux.getElementId());
            }
            return true;
        }
        return false;
    }

    @Override
    public void clearChildren() {
        for (MrNode<V> child : mChildren.values()) {
            removeChild(child);
        }
    }

    @Override
    public void clearParent() {
        mParent.removeChild(this);
    }

    @Override
    public MrMapNode<K,V> getRoot() {
        MrMapNode<K,V> node = this;
        while (node.hasParent()) {
            node = node.getParent();
        }
        return node;
    }

    @Override
    public int childrenSize() {
        return mChildren.size();
    }

    public int size() {
        return mTree.size();
    }

    @Override
    public int compareTo(MrNode<V> node) {
        if (node.getDepth() < mDepth) {
            return 1;
        } else if (node.getDepth() > mDepth) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public Iterator<MrMapNode<K,V>> iterator() {
        return new MrNodeMapIterator(this);
    }

    public MrMapNode<K,V> find(K key) {
        return mTree.get(key);
    }

    public boolean removeByKey(K key) {
        MrMapNode<K,V> n = find(key);
        return n.getParent().removeChild(n);
    }

    public boolean containsKey(K key) {
        return mTree.containsKey(key);
    }

    @Override
    public boolean put(K key, MrMapNode<K, V> node) {
        MrMapNode<K,V> n = (MrMapNode<K, V>) node;
        if (n.hasParent()) {
            n.getParent().removeChild(n);
        }
        n.setParent(this);
        for (MrNode<V> m : n) {
            MrMapNode<K,V> aux = (MrMapNode<K, V>) m;
            aux.setDepth();
            mTree.put(aux.getElementId(), aux);
        }
        return mChildren.put(key, n) != null;
    }

    private class MrNodeMapIterator implements Iterator<MrMapNode<K, V>> {
        private MrMapNode<K, V> mCurrent;
        private LinkedList<MrMapNode<K, V>> mQueue;

        public MrNodeMapIterator(MrMapNode<K, V> root) {
            mCurrent = root;
            mQueue = new LinkedList<MrMapNode<K, V>>();
            mQueue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !mQueue.isEmpty();
        }

        @Override
        public MrMapNode<K, V> next() {
            mQueue.addAll(mCurrent.getChildren());
            MrMapNode<K, V> aux = mCurrent;
            mCurrent = mQueue.pollFirst();
            return aux;
        }

        @Override
        public void remove() {

        }
    }
}
