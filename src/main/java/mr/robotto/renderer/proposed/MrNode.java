package mr.robotto.renderer.proposed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class MrNode<T> implements Comparable<MrNode<T>>, Iterable<MrNode<T>> {
    private MrNode<T> mParent;
    private T mData;
    private ArrayList<MrNode<T>> mChildren;
    private int mDepth;

    public MrNode(MrNode<T> parent, T data) {
        init();
        if (parent != null) {
            parent.addChild(this);
        }
        mData = data;
    }

    private void init() {
        mChildren = new ArrayList<MrNode<T>>();
        mParent = null;
        mDepth = 0;
    }

    private void setParent(MrNode<T> parent) {
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

    public MrNode<T> getParent() {
        return mParent;
    }

    public Collection<MrNode<T>> getChildren() {
        return mChildren;
    }

    public boolean addChild(MrNode<T> node) {
        if (node.hasParent()) {
            node.getParent().removeChild(node);
        }
        node.setParent(this);
        return mChildren.add(node);
    }

    public boolean removeChild(MrNode<T> node) {
        if (mChildren.remove(node)) {
            node.setParent(null);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<MrNode<T>> iterator() {
        return new MrNodeIterator(this);
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

    public class MrNodeIterator implements Iterator<MrNode<T>> {

        private MrNode<T> mCurrent;
        private LinkedList<MrNode<T>> mQueue;

        public MrNodeIterator(MrNode<T> root) {
            mCurrent = root;
            mQueue = new LinkedList<MrNode<T>>();
            mQueue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !mQueue.isEmpty();
        }

        @Override
        public MrNode<T> next() {
            mQueue.addAll(mCurrent.getChildren());
            MrNode<T> aux = mCurrent;
            mCurrent = mQueue.pollFirst();
            return aux;
        }

        @Override
        public void remove() {

        }
    }
}
