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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mr.robotto.collections.core.MrMapFunction;

/**
 * Created by Aarón on 26/12/2014.
 */
public class MrMapTree<K, V> implements Iterable<V> {

    private final MrMapFunction<K, V> mMapFunction;
    private HashMap<K, MrMapTreeNode<V>> mTree;
    private MrMapTreeNode<V> mRoot;

    public MrMapTree(V root, MrMapFunction<K, V> mapFunction) {
        init();
        mMapFunction = mapFunction;
        mRoot = new MrMapTreeNode<V>(root);
        mTree.put(mMapFunction.getIdOf(root), mRoot);
    }

    private MrMapTree(MrMapTreeNode<V> root, MrMapTree<K, V> mapTree) {
        this(root.getData(), mapTree.getMapFunction());

    }

    private void init() {
        mTree = new HashMap<K, MrMapTreeNode<V>>();
    }

    public MrMapFunction<K, V> getMapFunction() {
        return mMapFunction;
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

    public Iterator<V> breadthTraversal() {
        return new MrBreadthTraversalIterator(mRoot);
    }

    public Iterator<V> depthTraversal() {
        return new MrDepthTraversalIterator(mRoot);
    }

    public Iterator<V> parentTraversal(K key) {
        return new MrParentTraversalIterator(mTree.get(key));
    }


    @Override
    public Iterator<V> iterator() {
        return null;
    }

    //implementar multiples traversals

    private class MrParentKeyValueBreadthTraversalIterator implements Iterator<Map.Entry<K, V>> {
        private LinkedList<Map.Entry<K, V>> mQueue;

        private MrParentKeyValueBreadthTraversalIterator(MrMapTreeNode<V> current) {
            mQueue = new LinkedList<Map.Entry<K, V>>();
            //mQueue.add(new Map.Entry<K,V>());
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Map.Entry<K, V> next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }

    private class MrParentTraversalIterator implements Iterator<V> {
        private MrMapTreeNode<V> mCurrent;

        public MrParentTraversalIterator(MrMapTreeNode<V> current) {
            mCurrent = current;
        }

        @Override
        public boolean hasNext() {
            return mCurrent.hasParent();
        }

        @Override
        public V next() {
            V data = mCurrent.getData();
            mCurrent = mCurrent.getParent();
            return data;
        }

        @Override
        public void remove() {

        }
    }

    private class MrDepthTraversalIterator implements Iterator<V> {
        private LinkedList<MrMapTreeNode<V>> mStack;

        private MrDepthTraversalIterator(MrMapTreeNode<V> current) {
            mStack = new LinkedList<MrMapTreeNode<V>>();
            mStack.add(current);
        }

        @Override
        public boolean hasNext() {
            return !mStack.isEmpty();
        }

        @Override
        public V next() {
            MrMapTreeNode<V> node = mStack.pollLast();
            mStack.addAll(node.getChildren());
            return node.getData();
        }

        @Override
        public void remove() {

        }
    }

    private class MrBreadthTraversalIterator implements Iterator<V> {
        private LinkedList<MrMapTreeNode<V>> mQueue;

        public MrBreadthTraversalIterator(MrMapTreeNode<V> current) {
            mQueue = new LinkedList<MrMapTreeNode<V>>();
            mQueue.add(current);
        }

        @Override
        public boolean hasNext() {
            return !mQueue.isEmpty();
        }

        @Override
        public V next() {
            MrMapTreeNode<V> node = mQueue.pollFirst();
            mQueue.addAll(node.getChildren());
            return node.getData();
            //mQueue.addAll(mCurrent.getChildren());
            //MrMapTreeNode<V> aux = mCurrent;
            //mCurrent = mQueue.pollFirst();
            //return aux.getData();
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
