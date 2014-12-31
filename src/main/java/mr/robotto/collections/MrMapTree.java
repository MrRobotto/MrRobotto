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

    public final static int BREADTH_TRAVERSAL = 0;
    public final static int DEPTH_TRAVERSAL = 1;

    private final MrMapFunction<K, V> mMapFunction;
    private final MrMapTreeNode mRoot;
    private HashMap<K, MrMapTreeNode> mTree;
    private int mTraversalMode;

    public MrMapTree(K rootKey, V root, MrMapFunction<K, V> mapFunction) {
        init();
        mMapFunction = mapFunction;
        mRoot = new MrMapTreeNode(rootKey, root);
        mTree.put(rootKey, mRoot);
        mTraversalMode = BREADTH_TRAVERSAL;
    }

    public MrMapTree(V root, MrMapFunction<K, V> mapFunction) {
        this(mapFunction.getIdOf(root), root, mapFunction);
    }

    private MrMapTree(MrMapTreeNode root, MrMapTree<K, V> mapTree) {
        this(root.getData(), mapTree.getMapFunction());
        Iterator<Map.Entry<K, V>> it = mapTree.parentKeyChildValueTraversal(mRoot.getKey());
        while (it.hasNext()) {
            Map.Entry<K, V> entry = it.next();
            addChild(entry.getKey(), entry.getValue());
        }
    }

    private void init() {
        mTree = new HashMap<K, MrMapTreeNode>();
    }

    public int getTraversalMode() {
        return mTraversalMode;
    }

    public void setTraversalMode(int traversalMode) {
        mTraversalMode = traversalMode;
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

    public boolean addChild(K parentKey, K childKey, V data) {
        if (!mTree.containsKey(parentKey)) {
            return false;
        }
        if (mTree.containsKey(childKey)) {
            removeChild(childKey);
        }
        MrMapTreeNode parent = mTree.get(parentKey);
        MrMapTreeNode node = new MrMapTreeNode(childKey, data);
        parent.addChild(node);
        mTree.put(childKey, node);
        return true;
    }

    public boolean addChild(K parentKey, V data) {
        return addChild(parentKey, mMapFunction.getIdOf(data), data);
    }

    private void removeChildRecursive(MrMapTreeNode node) {
        if (node.hasChildren()) {
            while (node.hasChildren()) {
                removeChildRecursive(node.getChildren().get(0));
            }
        } else {
            node.getParent().removeChild(node);
            mTree.remove(node.getKey());
        }
    }

    public boolean removeChild(K key) {
        if (!mTree.containsKey(key))
            return false;
        MrMapTreeNode node = mTree.get(key);
        removeChildRecursive(node);
        if (node.hasParent()) {
            node.getParent().removeChild(node);
        }
        mTree.remove(node.getKey());
        return true;
    }

    public List<V> getChildrenOf(K key) {
        Collection<MrMapTreeNode> nodes = mTree.get(key).getChildren();
        ArrayList<V> list = new ArrayList<>(nodes.size());
        for (MrMapTreeNode node : nodes) {
            list.add(node.getData());
        }
        return list;
    }

    public MrMapTree<K, V> getSubTree(K key) {
        return new MrMapTree<K, V>(mTree.get(key), this);
    }

    public Iterator<V> parentTraversal(K key) {
        return new MrParentTraversalIterator(mTree.get(key));
    }

    public Iterator<V> breadthTraversal(K key) {
        return new MrBreadthTraversalIterator(mTree.get(key));
    }

    public Iterator<V> depthTraversal(K key) {
        return new MrDepthTraversalIterator(mTree.get(key));
    }

    public Iterator<Map.Entry<K, V>> parentKeyChildValueTraversal(K key) {
        return new MrParentKeyChildValueTraversalIterator(mTree.get(key));
    }

    public Iterator<V> breadthTraversal() {
        return new MrBreadthTraversalIterator(mRoot);
    }

    public Iterator<V> depthTraversal() {
        return new MrDepthTraversalIterator(mRoot);
    }

    public Iterator<Map.Entry<K, V>> parentKeyChildValueTraversal() {
        return new MrParentKeyChildValueTraversalIterator(mRoot);
    }


    @Override
    public Iterator<V> iterator() {
        if (mTraversalMode == BREADTH_TRAVERSAL)
            return breadthTraversal();
        if (mTraversalMode == DEPTH_TRAVERSAL)
            return depthTraversal();
        return null;
    }

    //implementar multiples traversals

    private class MrParentKeyChildValueTraversalIterator implements Iterator<Map.Entry<K, V>> {
        private final LinkedList<MrMapTreeNode> mQueue;

        public MrParentKeyChildValueTraversalIterator(MrMapTreeNode current) {
            mQueue = new LinkedList<MrMapTreeNode>();
            mQueue.add(current);
        }

        private Map.Entry<K, V> createEntry(final MrMapTreeNode node) {
            return new Map.Entry<K, V>() {
                @Override
                public boolean equals(Object object) {
                    return false;
                }

                @Override
                public K getKey() {
                    if (node.hasParent())
                        return node.getParent().getKey();
                    else
                        return null;
                }

                @Override
                public V getValue() {
                    return node.getData();
                }

                @Override
                public int hashCode() {
                    K key = getKey();
                    if (key != null)
                        return getKey().hashCode();
                    else
                        return 0;
                }

                @Override
                public V setValue(V object) {
                    return null;
                }
            };
        }

        @Override
        public boolean hasNext() {
            return !mQueue.isEmpty();
        }

        @Override
        public Map.Entry<K, V> next() {
            MrMapTreeNode node = mQueue.pollFirst();
            mQueue.addAll(node.getChildren());
            return createEntry(node);
        }

        @Override
        public void remove() {

        }
    }

    private class MrParentTraversalIterator implements Iterator<V> {
        private MrMapTreeNode mCurrent;

        public MrParentTraversalIterator(MrMapTreeNode current) {
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
        private final LinkedList<MrMapTreeNode> mStack;

        private MrDepthTraversalIterator(MrMapTreeNode current) {
            mStack = new LinkedList<MrMapTreeNode>();
            mStack.add(current);
        }

        @Override
        public boolean hasNext() {
            return !mStack.isEmpty();
        }

        @Override
        public V next() {
            MrMapTreeNode node = mStack.pollLast();
            mStack.addAll(node.getChildren());
            return node.getData();
        }

        @Override
        public void remove() {

        }
    }

    private class MrBreadthTraversalIterator implements Iterator<V> {
        private final LinkedList<MrMapTreeNode> mQueue;

        public MrBreadthTraversalIterator(MrMapTreeNode current) {
            mQueue = new LinkedList<MrMapTreeNode>();
            mQueue.add(current);
        }

        @Override
        public boolean hasNext() {
            return !mQueue.isEmpty();
        }

        @Override
        public V next() {
            MrMapTreeNode node = mQueue.pollFirst();
            mQueue.addAll(node.getChildren());
            return node.getData();
        }

        @Override
        public void remove() {

        }
    }

    private class MrMapTreeNode {
        private final V mData;
        private MrMapTreeNode mParent;
        private List<MrMapTreeNode> mChildren;
        private K mKey;

        public MrMapTreeNode(K key, V data) {
            init();
            mKey = key;
            mData = data;
        }

        private void init() {
            mChildren = new ArrayList<MrMapTreeNode>();
            mParent = null;
        }

        public MrMapTreeNode getParent() {
            return mParent;
        }

        private void setParent(MrMapTreeNode parent) {
            mParent = parent;
        }

        public V getData() {
            return mData;
        }

        public K getKey() {
            return mKey;
        }

        public boolean addChild(MrMapTreeNode node) {
            node.setParent(this);
            return mChildren.add(node);
        }

        public boolean removeChild(MrMapTreeNode node) {
            node.setParent(null);
            return mChildren.remove(node);
        }

        public List<MrMapTreeNode> getChildren() {
            return mChildren;
        }

        public boolean hasChildren() {
            return !mChildren.isEmpty();
        }

        public boolean hasParent() {
            return mParent != null;
        }

        @Override
        public String toString() {
            return "MrMapTreeNode{" +
                    "mKey=" + mKey +
                    ", mData=" + mData +
                    '}';
        }
    }
}
