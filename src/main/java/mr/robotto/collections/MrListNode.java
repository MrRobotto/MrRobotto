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
import java.util.Iterator;
import java.util.LinkedList;

import mr.robotto.collections.core.MrNode;

/**
 * Created by Aarón on 17/11/2014.
 */
public class MrListNode<T> implements MrNode<T>, Iterable<MrListNode<T>> {
    private MrListNode<T> mParent;
    private T mData;
    private ArrayList<MrListNode<T>> mChildren;
    private int mDepth;

    public MrListNode(MrNode<T> parent, T data) {
        init();
        if (parent != null) {
            parent.addChild(this);
        }
        mData = data;
    }

    private void init() {
        mChildren = new ArrayList<MrListNode<T>>();
        mParent = null;
        mDepth = 0;
    }

    private void setParent(MrListNode<T> parent) {
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

    public T getData() {
        return mData;
    }

    public int getDepth() {
        return mDepth;
    }

    @Override
    public boolean isLeaf() {
        return mChildren.isEmpty();
    }

    public boolean hasParent() {
        return mParent != null;
    }

    public MrListNode<T> getParent() {
        return mParent;
    }

    public Collection<MrListNode<T>> getChildren() {
        return mChildren;
    }

    public boolean addChild(MrNode<T> node) {
        MrListNode<T> n = (MrListNode<T>) node;
        if (node.hasParent()) {
            node.getParent().removeChild(node);
        }
        n.setParent(this);
        for (MrNode<T> m : n) {
            MrListNode<T> aux = (MrListNode<T>) m;
            aux.setDepth();
        }
        return mChildren.add(n);
    }

    public boolean removeChild(MrNode<T> node) {
        MrListNode<T> n = (MrListNode<T>) node;
        if (mChildren.remove(node)) {
            n.setParent(null);
            for (MrNode<T> m : n) {
                MrListNode<T> aux = (MrListNode<T>) m;
                aux.setDepth();
            }
            return true;
        }
        return false;
    }

    public void clearChildren() {
        while (!mChildren.isEmpty()) {
            MrNode<T> child = mChildren.get(0);
            removeChild(child);
        }
    }

    public void clearParent() {
        if (mParent != null) {
            mParent.removeChild(this);
        }
    }

    public MrNode<T> getRoot() {
        MrNode<T> node = this;
        while (node.hasParent()) {
            node = node.getParent();
        }
        return node;
    }

    @Override
    public int childrenSize() {
        return mChildren.size();
    }

    @Override
    public Iterator<MrListNode<T>> iterator() {
        return new MrListNodeIterator(this);
    }

    @Override
    public int compareTo(MrNode<T> node) {
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

    private class MrListNodeIterator implements Iterator<MrListNode<T>> {

        private MrListNode<T> mCurrent;
        private LinkedList<MrListNode<T>> mQueue;

        public MrListNodeIterator(MrListNode<T> root) {
            mCurrent = root;
            mQueue = new LinkedList<MrListNode<T>>();
            mQueue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !mQueue.isEmpty();
        }

        @Override
        public MrListNode<T> next() {
            mQueue.addAll(mCurrent.getChildren());
            MrListNode<T> aux = mCurrent;
            mCurrent = mQueue.pollFirst();
            return aux;
        }

        @Override
        public void remove() {
        }
    }

}
