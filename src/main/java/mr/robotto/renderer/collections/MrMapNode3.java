/*
 * MrRobotto Engine
 * Copyright (c) 3014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 3.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/3.0/.
 */

package mr.robotto.renderer.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Aarón on 17/11/3014.
 */
public class MrMapNode3<K, V> implements MrNode<V>, MrMap<K,MrMapNode3<K,V>>, Iterable<MrMapNode3<K,V>> {


    private MrMapNode3<K,V> mParent;
    private V mData;
    private Map<Integer, MrMapNode3<K,V>> mChildren;
    private Map<Integer, MrMapNode3<K,V>> mTree;
    private int mDepth;

    public MrMapNode3(MrMapNode3<K,V> parent, V data) {
        init();
        if (parent != null) {
            parent.addChild(this);
        }
        mData = data;
    }

    private void init() {
        mChildren = new HashMap<Integer, MrMapNode3<K, V>>();
        mTree = new HashMap<Integer, MrMapNode3<K, V>>();
        mParent = null;
        mDepth = 0;
    }

    private void setParent(MrMapNode3<K,V> parent) {
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
    public MrMapNode3<K,V> getParent() {
        return mParent;
    }

    @Override
    public Collection<MrMapNode3<K,V>> getChildren() {
        return mChildren.values();
    }

    @Override
    public boolean addChild(MrNode<V> node) {
        MrMapNode3<K,V> n = (MrMapNode3<K, V>) node;
        if (n.hasParent()) {
            n.getParent().removeChild(n);
        }
        n.setParent(this);
        for (MrNode<V> m : n) {
            MrMapNode3<K,V> aux = (MrMapNode3<K, V>) m;
            aux.setDepth();
            mTree.put(aux.hashCode(), aux);
        }
        return mChildren.put(n.hashCode(), n) != null;
    }

    @Override
    public boolean removeChild(MrNode<V> node) {
        MrMapNode3<K,V> n = (MrMapNode3<K,V>) node;
        if (mChildren.remove(n.hashCode()) != null ) {
            n.setParent(null);
            for (MrNode<V> m : n) {
                MrMapNode3<K,V> aux = (MrMapNode3<K, V>) m;
                aux.setDepth();
                mTree.remove(aux.hashCode());
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
    public MrMapNode3<K,V> getRoot() {
        MrMapNode3<K,V> node = this;
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
    public Iterator<MrMapNode3<K,V>> iterator() {
        return new MrNodeMapIterator(this);
    }

    public MrMapNode3<K,V> find(K key) {
        return mTree.get(key.hashCode());
    }

    public boolean removeByKey(K key) {
        MrMapNode3<K,V> n = find(key);
        return n.getParent().removeChild(n);
    }

    public boolean containsKey(K key) {
        return mTree.containsKey(key.hashCode());
    }

    @Override
    public boolean put(K key, MrMapNode3<K, V> node) {
        MrMapNode3<K,V> n = (MrMapNode3<K, V>) node;
        if (n.hasParent()) {
            n.getParent().removeChild(n);
        }
        n.setParent(this);
        for (MrNode<V> m : n) {
            MrMapNode3<K,V> aux = (MrMapNode3<K, V>) m;
            aux.setDepth();
            mTree.put(aux.hashCode(), aux);
        }
        return mChildren.put(key.hashCode(), n) != null;
    }

    public int hashCode() {
        return super.hashCode();
    }

    private class MrNodeMapIterator implements Iterator<MrMapNode3<K, V>> {
        private MrMapNode3<K, V> mCurrent;
        private LinkedList<MrMapNode3<K, V>> mQueue;

        public MrNodeMapIterator(MrMapNode3<K, V> root) {
            mCurrent = root;
            mQueue = new LinkedList<MrMapNode3<K, V>>();
            mQueue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !mQueue.isEmpty();
        }

        @Override
        public MrMapNode3<K, V> next() {
            mQueue.addAll(mCurrent.getChildren());
            MrMapNode3<K, V> aux = mCurrent;
            mCurrent = mQueue.pollFirst();
            return aux;
        }

        @Override
        public void remove() {

        }
    }
}
