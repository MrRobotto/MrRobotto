package mr.robotto.renderer.proposed.containers;

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

    public Collection<? extends MrNode<T>> getChildren() {
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

    public void clearChildren() {
        for (MrNode<T> child : mChildren) {
            removeChild(child);
        }
    }

    public void clearParent() {
        mParent.removeChild(this);
    }

    public MrNode<T> getRoot() {
        MrNode<T> node = this;
        while (node.hasParent()) {
            node = node.getParent();
        }
        return node;
    }

    @Override
    public Iterator<MrNode<T>> iterator() {
        return new MrNodeIterator<MrNode<T>>(this);
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

    public class MrNodeIterator<V extends MrNode<T>> implements Iterator<V> {

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
            for (MrNode<T> node : mCurrent.getChildren()) {
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
