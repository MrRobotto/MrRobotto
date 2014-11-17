package mr.robotto.renderer.proposed.containers;

import java.util.Collection;

/**
 * Created by Aarón on 17/11/2014.
 */
public interface MrINode<T> extends Comparable<MrINode<T>> {

    public T getData();

    public int getDepth();

    public boolean hasParent();

    public MrINode<T> getParent();

    public Collection<? extends MrINode<T>> getChildren();

    public boolean addChild(MrINode<T> node);

    public boolean removeChild(MrINode<T> node);

    public void clearChildren();

    public void clearParent();

    public MrINode<T> getRoot();
}
