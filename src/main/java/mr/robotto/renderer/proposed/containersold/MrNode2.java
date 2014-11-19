/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.proposed.containersold;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class MrNode2<T> implements Comparable<MrNode2<T>>, Iterable<MrNode2<T>> {
    private MrNode2<T> mParent;
    private T mData;
    private ArrayList<MrNode2<T>> mChildren;
    private int mDepth;

    public MrNode2(MrNode2<T> parent, T data) {
        init();
        if (parent != null) {
            parent.addChild(this);
        }
        mData = data;
    }

    private void init() {
        mChildren = new ArrayList<MrNode2<T>>();
        mParent = null;
        mDepth = 0;
    }

    private void setParent(MrNode2<T> parent) {
        mParent = parent;
        if (mParent != null) {
            mDepth = mParent.getDepth() + 1;
        } else {
            mDepth = 0;
        }
    }

    public T getData() {
        return mData;
    }

    public int getDepth() {
        return mDepth;
    }

    public boolean hasParent() {
        return mParent != null;
    }

    public MrNode2<T> getParent() {
        return mParent;
    }

    public Collection<? extends MrNode2<T>> getChildren() {
        return mChildren;
    }

    public boolean addChild(MrNode2<T> node) {
        if (node.hasParent()) {
            node.getParent().removeChild(node);
        }
        node.setParent(this);
        return mChildren.add(node);
    }

    public boolean removeChild(MrNode2<T> node) {
        if (mChildren.remove(node)) {
            node.setParent(null);
            return true;
        }
        return false;
    }

    public void clearChildren() {
        for (MrNode2<T> child : mChildren) {
            removeChild(child);
        }
    }

    public void clearParent() {
        mParent.removeChild(this);
    }

    public MrNode2<T> getRoot() {
        MrNode2<T> node = this;
        while (node.hasParent()) {
            node = node.getParent();
        }
        return node;
    }

    @Override
    public Iterator<MrNode2<T>> iterator() {
        return new MrNodeIterator<MrNode2<T>>(this);
    }

    @Override
    public int compareTo(MrNode2<T> node) {
        if (node.getDepth() < mDepth) {
            return 1;
        } else if (node.getDepth() > mDepth) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "MrNode{" + mData.toString() +'}';
    }

    public class MrNodeIterator<V extends MrNode2<T>> implements Iterator<V> {

        private V mCurrent;
        private LinkedList<V> mQueue;

        public MrNodeIterator(V root) {
            mCurrent = root;
            mQueue = new LinkedList<V>();
            mQueue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !mQueue.isEmpty();
        }

        @Override
        public V next() {
            for (MrNode2<T> node : mCurrent.getChildren()) {
                V aux = (V) node;
            }

            // mQueue.addAll(mCurrent.getChildren());
            V aux = mCurrent;
            mCurrent = mQueue.pollFirst();
            return aux;
        }

        @Override
        public void remove() {

        }
    }
}
