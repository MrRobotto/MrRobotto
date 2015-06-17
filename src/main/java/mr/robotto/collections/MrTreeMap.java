/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
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

/**
 * Tree Structure. This tree can saves nodes in a hierarchically way.
 * Moreover finding an element is done in O(1) because all ellements are hashed using a
 * {@link MrMapFunction MrMapFuncion}
 */
public class MrTreeMap<K, V> implements Iterable<V> {

    /**
     * Breadth first traversal mode
     */
    public final static int BREADTH_TRAVERSAL = 0;
    /**
     * Depth first traversal mode
     */
    public final static int DEPTH_TRAVERSAL = 1;

    private final MrMapFunction<K, V> mMapFunction;
    private final HashMap<K, MrTreeMapNode> mTree = new HashMap<K, MrTreeMapNode>();
    private MrTreeMapNode mRoot;
    private int mTraversalMode;

    /**
     * Creates an empty MrTreeMap
     *
     * @param mapFunction map-function used for this tree
     */
    public MrTreeMap(MrMapFunction<K, V> mapFunction) {
        mRoot = null;
        mMapFunction = mapFunction;
        mTraversalMode = BREADTH_TRAVERSAL;
    }

    /**
     * Creates a MrTreeMap with a root element
     * @param root root element of tree
     * @param mapFunction map-function used for this tree
     */
    public MrTreeMap(V root, MrMapFunction<K, V> mapFunction) {
        mMapFunction = mapFunction;
        mRoot = new MrTreeMapNode(root);
        mTree.put(mMapFunction.getKeyOf(root), mRoot);
        mTraversalMode = BREADTH_TRAVERSAL;
    }

    private MrTreeMap(MrTreeMapNode root, MrTreeMap<K, V> mapTree) {
        this(root.getData(), mapTree.getMapFunction());
        Iterator<Map.Entry<K, V>> it = mapTree.parentKeyChildValueTraversalByKey(mMapFunction.getKeyOf(root.getData()));
        while (it.hasNext()) {
            Map.Entry<K, V> entry = it.next();
            addChildByKey(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Gets the current traversal mode
     * @return
     */
    public int getTraversalMode() {
        return mTraversalMode;
    }

    /**
     * Sets the current traversal mode
     * @param traversalMode
     */
    public void setTraversalMode(int traversalMode) {
        mTraversalMode = traversalMode;
    }

    /**
     * Gets the current map function of this tree
     * @return
     */
    public MrMapFunction<K, V> getMapFunction() {
        return mMapFunction;
    }

    /**
     * Gets the root object of this tree
     * @return root object or null if the tree is empty
     */
    public V getRoot() {
        if (mRoot == null) {
            return null;
        }
        return mRoot.getData();
    }

    /**
     * Finds an object in this tree using its key
     * @param key object key, this is the result of tree's map-function
     * @return object or null if the key does not exists
     */
    public V findByKey(K key) {
        MrTreeMapNode node = mTree.get(key);
        if (node == null) {
            return null;
        }
        return node.getData();
    }

    /**
     * Checks if key exists
     * @param key
     * @return true if the key exists, if it does not, false
     */
    public boolean containsKey(K key) {
        return mTree.containsKey(key);
    }

    /**
     * Checks if the key returned by map-function is in inside the tree
     * @param data
     * @return
     */
    public boolean contains(V data) {
        K key = mMapFunction.getKeyOf(data);
        return mTree.containsKey(key);
    }

    /**
     * Clears all elements
     */
    public void clear() {
        mRoot = null;
        mTree.clear();
    }

    /**
     * Gets the keys collection
     * @return All keys already in use in this tree
     */
    public Collection<K> keys() {
        return mTree.keySet();
    }

    /**
     * Number of elements in this tree
     * @return number of elements
     */
    public int size() {
        return mTree.size();
    }

    /**
     * Adds a child to the node
     * @param parentKey parent node key
     * @param data data to be used as a child
     * @return true if the insertion has been done, false otherwise
     */
    public boolean addChildByKey(K parentKey, V data) {
        if (mRoot == null && parentKey == null) {
            MrTreeMapNode node = new MrTreeMapNode(data);
            mTree.put(mMapFunction.getKeyOf(data), node);
        }
        if (!mTree.containsKey(parentKey)) {
            return false;
        }
        K childKey = mMapFunction.getKeyOf(data);
        if (mTree.containsKey(childKey)) {
            removeByKey(childKey);
        }
        MrTreeMapNode parent = mTree.get(parentKey);
        MrTreeMapNode node = new MrTreeMapNode(data);
        parent.addChild(node);
        mTree.put(childKey, node);
        return true;
    }

    /**
     * Adds a child to the node
     * @param parent parent object
     * @param data child object to be inserted
     * @return true if the insertion has been done, false otherwise
     */
    public boolean addChild(V parent, V data) {
        return addChildByKey(mMapFunction.getKeyOf(parent), data);
    }

    private void removeChildRecursive(MrTreeMapNode node) {
        if (node.hasChildren()) {
            while (node.hasChildren()) {
                removeChildRecursive(node.getChildren().get(0));
            }
        } else {
            node.getParent().removeChild(node);
            mTree.remove(mMapFunction.getKeyOf(node.getData()));
        }
    }

    /**
     * Removes a certain element in this tree
     * @param key of the element
     * @return
     */
    public boolean removeByKey(K key) {
        if (!mTree.containsKey(key))
            return false;
        MrTreeMapNode node = mTree.get(key);
        removeChildRecursive(node);
        if (node.hasParent()) {
            node.getParent().removeChild(node);
        }
        mTree.remove(mMapFunction.getKeyOf(node.getData()));
        return true;
    }

    /**
     * Removes a certain element from this tree
     * @param data data object to be removed
     * @return true if the deletion has been done, false otherwise
     */
    public boolean remove(V data) {
        return removeByKey(mMapFunction.getKeyOf(data));
    }

    /**
     * Gets the children of an object using its key
     * @param key node
     * @return the children of node
     */
    public List<V> getChildrenOfByKey(K key) {
        Collection<MrTreeMapNode> nodes = mTree.get(key).getChildren();
        ArrayList<V> list = new ArrayList<>(nodes.size());
        for (MrTreeMapNode node : nodes) {
            list.add(node.getData());
        }
        return list;
    }

    /**
     * Returns the parent of the element
     * @param data element
     * @return the parent of the element, null if it has no parent
     */
    public V getParentOf(V data) {
        K key = mMapFunction.getKeyOf(data);
        MrTreeMapNode node = mTree.get(key);
        MrTreeMapNode parent = node.getParent();
        if (parent == null) {
            return null;
        }
        return node.getParent().getData();
    }

    /**
     * Gets the parent of an element using its key
     * @param key key of the element
     * @return the parent of the current element
     */
    public V getParentOfByKey(K key) {
        MrTreeMapNode node = mTree.get(key);
        MrTreeMapNode parent = node.getParent();
        if (parent == null) {
            return null;
        }
        return node.getParent().getData();
    }

    /**
     * Gets the children of an element
     * @param data the object
     * @return the children of passed object
     */
    public List<V> getChildrenOf(V data) {
        return getChildrenOfByKey(mMapFunction.getKeyOf(data));
    }

    /**
     * Gets the subtree from key element using key
     * @param key key of object
     * @return
     */
    public MrTreeMap<K, V> getSubTreeByKey(K key) {
        return new MrTreeMap<K, V>(mTree.get(key), this);
    }

    /**
     * Gets the subtree from key element
     * @param data object
     * @return
     */
    public MrTreeMap<K, V> getSubTree(V data) {
        return getSubTreeByKey(mMapFunction.getKeyOf(data));
    }

    public Iterator<V> parentTraversalByKey(K key) {
        return new MrParentTraversalIterator(mTree.get(key));
    }

    public Iterator<V> breadthTraversalByKey(K key) {
        return new MrBreadthTraversalIterator(mTree.get(key));
    }

    public Iterator<V> depthTraversalByKey(K key) {
        return new MrDepthTraversalIterator(mTree.get(key));
    }

    public Iterator<Map.Entry<K, V>> parentKeyChildValueTraversalByKey(K key) {
        return new MrParentKeyChildValueTraversalIterator(mTree.get(key));
    }

    public Iterator<V> parentTraversal(V data) {
        return parentTraversalByKey(mMapFunction.getKeyOf(data));
    }

    public Iterator<V> breadthTraversal(V data) {
        return breadthTraversalByKey(mMapFunction.getKeyOf(data));
    }

    public Iterator<V> depthTraversal(V data) {
        return depthTraversalByKey(mMapFunction.getKeyOf(data));
    }

    public Iterator<Map.Entry<K, V>> parentKeyChildValueTraversal(V data) {
        return parentKeyChildValueTraversalByKey(mMapFunction.getKeyOf(data));
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

    private class MrParentKeyChildValueTraversalIterator implements Iterator<Map.Entry<K, V>> {
        private final LinkedList<MrTreeMapNode> mQueue;

        public MrParentKeyChildValueTraversalIterator(MrTreeMapNode current) {
            mQueue = new LinkedList<MrTreeMapNode>();
            mQueue.add(current);
        }

        private Map.Entry<K, V> createEntry(final MrTreeMapNode node) {
            return new Map.Entry<K, V>() {
                @Override
                public boolean equals(Object object) {
                    return false;
                }

                @Override
                public K getKey() {
                    if (node.hasParent())
                        return mMapFunction.getKeyOf(node.getParent().getData());
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
            MrTreeMapNode node = mQueue.pollFirst();
            mQueue.addAll(node.getChildren());
            return createEntry(node);
        }

        @Override
        public void remove() {

        }
    }

    private class MrParentTraversalIterator implements Iterator<V> {
        private MrTreeMapNode mCurrent;

        public MrParentTraversalIterator(MrTreeMapNode current) {
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
        private final LinkedList<MrTreeMapNode> mStack;

        private MrDepthTraversalIterator(MrTreeMapNode current) {
            mStack = new LinkedList<MrTreeMapNode>();
            mStack.add(current);
        }

        @Override
        public boolean hasNext() {
            return !mStack.isEmpty();
        }

        @Override
        public V next() {
            MrTreeMapNode node = mStack.pollLast();
            mStack.addAll(node.getChildren());
            return node.getData();
        }

        @Override
        public void remove() {

        }
    }

    private class MrBreadthTraversalIterator implements Iterator<V> {
        private final LinkedList<MrTreeMapNode> mQueue;

        public MrBreadthTraversalIterator(MrTreeMapNode current) {
            mQueue = new LinkedList<MrTreeMapNode>();
            mQueue.add(current);
        }

        @Override
        public boolean hasNext() {
            return !mQueue.isEmpty();
        }

        @Override
        public V next() {
            MrTreeMapNode node = mQueue.pollFirst();
            mQueue.addAll(node.getChildren());
            return node.getData();
        }

        @Override
        public void remove() {

        }
    }

    private class MrTreeMapNode {
        private final V mData;
        private MrTreeMapNode mParent;
        private List<MrTreeMapNode> mChildren;

        public MrTreeMapNode(V data) {
            init();
            mData = data;
        }

        private void init() {
            mChildren = new ArrayList<MrTreeMapNode>();
            mParent = null;
        }

        public MrTreeMapNode getParent() {
            return mParent;
        }

        private void setParent(MrTreeMapNode parent) {
            mParent = parent;
        }

        public V getData() {
            return mData;
        }

        public boolean addChild(MrTreeMapNode node) {
            node.setParent(this);
            return mChildren.add(node);
        }

        public boolean removeChild(MrTreeMapNode node) {
            node.setParent(null);
            return mChildren.remove(node);
        }

        public List<MrTreeMapNode> getChildren() {
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
                    ", mData=" + mData +
                    '}';
        }
    }
}
