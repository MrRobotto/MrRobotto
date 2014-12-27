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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import mr.robotto.collections.core.MrMapFunction;

/**
 * Created by Aarón on 26/12/2014.
 */
public class MrMapTree<K, V> implements Iterable<V> {

    private HashMap<K, MrMapTreeNode<V>> mTree;
    private MrMapTreeNode<V> mRoot;
    private MrMapFunction<K, V> mMapFunction;

    public MrMapTree(V root, MrMapFunction<K, V> mapFunction) {
        init();
        mMapFunction = mapFunction;
        mRoot = new MrMapTreeNode<V>(root);
        mTree.put(mMapFunction.getIdOf(root), mRoot);
    }

    private void init() {
        mTree = new HashMap<K, MrMapTreeNode<V>>();
    }

    public V getRoot() {
        return mRoot.getData();
    }

    public V find(K key) {
        return mTree.get(key).getData();
    }

    public boolean hasKey(K key) {
        return mTree.containsKey(key);
    }

    public Collection<K> keys() {
        return mTree.keySet();
    }

    public int size() {
        return mTree.size();
    }

    public boolean addChild(K parentKey, V data) {
        if (!mTree.containsKey(parentKey))
            return false;
        K key = mMapFunction.getIdOf(data);
        if (mTree.containsKey(key)) {
            //quitarlo
        }
        MrMapTreeNode<V> parent = mTree.get(parentKey);
        MrMapTreeNode<V> node = new MrMapTreeNode<V>(data);
        parent.addChild(node);
        mTree.put(key, node);
        return true;
    }

    private void removeRecursive(MrMapTreeNode<V> node) {
        if (node.hasChildren()) {
            for (MrMapTreeNode<V> n : node.getChildren()) {
                removeRecursive(n);
            }
        } else {
            node.getParent().removeChild(node);
            mTree.remove(mMapFunction.getIdOf(node.getData()));
        }
    }

    public boolean remove(K key) {
        if (!mTree.containsKey(key))
            return false;
        removeRecursive(mTree.get(key));
        return true;
    }

    /*public MrMapTree<K,V> getSubTree(K root) {

    }*/

    @Override
    public Iterator<V> iterator() {
        return null;
    }

    private class MrMapTreeIterator implements Iterator<V> {
        private MrMapTreeNode<V> mCurrent;

        public MrMapTreeIterator(MrMapTreeNode<V> root) {
            mCurrent = root;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public V next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }

    private class MrMapTreeNode<V> {
        private MrMapTreeNode<V> mParent;
        private List<MrMapTreeNode<V>> mChildren;
        private V mData;

        public MrMapTreeNode(V data) {
            init();
            mData = data;
        }

        private void init() {
            mChildren = new ArrayList<MrMapTreeNode<V>>();
            mParent = null;
        }

        public MrMapTreeNode<V> getParent() {
            return mParent;
        }

        private void setParent(MrMapTreeNode<V> parent) {
            mParent = parent;
        }

        public V getData() {
            return mData;
        }

        public boolean addChild(MrMapTreeNode<V> node) {
            node.setParent(this);
            return mChildren.add(node);
        }

        public boolean removeChild(MrMapTreeNode<V> node) {
            node.setParent(null);
            return mChildren.remove(node);
        }

        public Collection<MrMapTreeNode<V>> getChildren() {
            return mChildren;
        }

        public boolean hasChildren() {
            return !mChildren.isEmpty();
        }

        public boolean hasParent() {
            return mParent != null;
        }
    }
}
