package mr.robotto.renderer.proposed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MrNode<T> implements Comparable<MrNode>, Iterable<T> {
    private MrNode mParent;
    private T mData;
    private ArrayList<MrNode> mChildren;
    private int mDepth;

    public MrNode(MrNode parent, T data) {
        init();
        if (parent != null) {
            parent.addChild(this);
        }
        mData = data;
    }

    private void init() {
        mChildren = new ArrayList<MrNode>();
        mParent = null;
        mDepth = 0;
    }

    private void setParent(MrNode parent) {
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

    public MrNode getParent() {
        return mParent;
    }

    public Collection<MrNode> getChildren() {
        return mChildren;
    }

    public boolean addChild(MrNode node) {
        if (node.hasParent()) {
            node.getParent().removeChild(node);
        }
        node.setParent(this);
        return mChildren.add(node);
    }

    public boolean removeChild(MrNode node) {
        if (mChildren.remove(node)) {
            node.setParent(null);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {

        return null;
    }

    @Override
    public int compareTo(MrNode node) {
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
}
