/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.proposed.deprecated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import mr.robotto.collections.core.MrMapFunction;

/**
 * Created by Aarón on 17/11/2014.
 */
public class MrMapNode<K, V> implements Iterable<MrMapNode<K, V>> {

    private MrMapNode<K, V> mParent;
    private V mData;
    private ArrayList<MrMapNode<K, V>> mChildren;
    private HashMap<K, MrMapNode<K, V>> mTree;
    private HashMap<K, V> mTreeValues;
    private int mDepth;

    private MrMapFunction<K, V> mMapFunction;

    public MrMapNode(MrMapNode<K, V> parent, V data, MrMapFunction<K, V> mapFunction) {
        init();
        setParent(parent);
        if (hasParent()) {
            mParent.addChild(this);
        }
        mData = data;
        mMapFunction = mapFunction;
    }

    public MrMapNode(V data, MrMapFunction<K, V> mapFunction) {
        this(null, data, mapFunction);
    }

    private void init() {
        mChildren = new ArrayList<MrMapNode<K, V>>();
        mTree = new HashMap<K, MrMapNode<K, V>>();
        mTreeValues = new HashMap<K, V>();
        mParent = null;
        mDepth = 0;
    }

    private void setDepth() {
        if (mParent != null) {
            mDepth = mParent.getDepth() + 1;
        } else {
            mDepth = 0;
        }
    }

    private K getElementId() {
        return mMapFunction.getKeyOf(mData);
    }

    public V getData() {
        return mData;
    }

    public int getDepth() {
        return mDepth;
    }

    public boolean isLeaf() {
        return mChildren.isEmpty();
    }

    public boolean hasParent() {
        return mParent != null;
    }

    public MrMapNode<K, V> getParent() {
        return mParent;
    }

    private void setParent(MrMapNode<K, V> parent) {
        mParent = parent;
        setDepth();
    }

    public Collection<MrMapNode<K, V>> getChildren() {
        return mChildren;
    }

    public boolean addChild(MrMapNode<K, V> node) {
        if (node.hasParent()) {
            node.getParent().removeChild(node);
        }
        node.setParent(this);
        for (MrMapNode<K, V> m : node) {
            m.setDepth();
            mTree.put(m.getElementId(), m);
            mTreeValues.put(m.getElementId(), m.getData());
        }
        return mChildren.add(node);
    }

    public boolean removeChild(MrMapNode<K, V> node) {
        if (mChildren.remove(node)) {
            node.setParent(null);
            for (MrMapNode<K, V> m : node) {
                m.setDepth();
                mTree.remove(m.getElementId());
                mTreeValues.remove(m.getElementId());
            }
            return true;
        }
        return false;
    }

    public void clearChildren() {
        while (!mChildren.isEmpty()) {
            removeChild(mChildren.get(0));
        }
    }

    public void clearParent() {
        mParent.removeChild(this);
    }

    public MrMapNode<K, V> getRoot() {
        MrMapNode<K, V> node = this;
        while (node.hasParent()) {
            node = node.getParent();
        }
        return node;
    }

    public int childrenSize() {
        return mChildren.size();
    }

    public int size() {
        return mTree.size();
    }

    public int compareTo(MrMapNode<K, V> node) {
        if (node.getDepth() < mDepth) {
            return 1;
        } else if (node.getDepth() > mDepth) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public Iterator<MrMapNode<K, V>> iterator() {
        return new MrNodeMapIterator(this);
    }

    public MrMapNode<K, V> findByKey(K key) {
        return mTree.get(key);
    }

    public boolean removeByKey(K key) {
        if (!hasKey(key)) {
            return false;
        }
        MrMapNode<K, V> n = findByKey(key);
        return n.getParent().removeChild(n);
    }

    public boolean hasKey(K key) {
        return mTree.containsKey(key);
    }

    public Collection<MrMapNode<K, V>> getNodes() {
        return mTree.values();
    }

    public Collection<K> keys() {
        return mTree.keySet();
    }

    public Collection<V> values() {
        return mTreeValues.values();
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