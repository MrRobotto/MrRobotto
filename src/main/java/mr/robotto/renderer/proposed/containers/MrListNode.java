package mr.robotto.renderer.proposed.containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Aarón on 17/11/2014.
 */
public class MrListNode<T> implements MrINode<T>, Iterable<MrListNode<T>> {
    private MrListNode<T> mParent;
    private T mData;
    private ArrayList<MrListNode<T>> mChildren;
    private int mDepth;

    public MrListNode(MrINode<T> parent, T data) {
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

    public MrListNode<T> getParent() {
        return mParent;
    }

    public Collection<MrListNode<T>> getChildren() {
        return mChildren;
    }

    //TODO: Change order of set parent and deletion/insertion
    public boolean addChild(MrINode<T> node) {
        MrListNode<T> n = (MrListNode<T>) node;
        if (node.hasParent()) {
            node.getParent().removeChild(node);
        }
        n.setParent(this);
        return mChildren.add(n);
    }

    public boolean removeChild(MrINode<T> node) {
        MrListNode<T> n = (MrListNode<T>) node;
        if (mChildren.remove(node)) {
            n.setParent(null);
            return true;
        }
        return false;
    }

    public void clearChildren() {
        for (MrINode<T> child : mChildren) {
            removeChild(child);
        }
    }

    public void clearParent() {
        mParent.removeChild(this);
    }

    public MrINode<T> getRoot() {
        MrINode<T> node = this;
        while (node.hasParent()) {
            node = node.getParent();
        }
        return node;
    }

    @Override
    public Iterator<MrListNode<T>> iterator() {
        return new MrListNodeIterator(this);
    }

    @Override
    public int compareTo(MrINode<T> node) {
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
