package mr.robotto.renderer.collections;

import java.util.Iterator;

public interface MrContainer<V> extends Iterable<V> {

    public boolean add(V v);

    public void clear();

    public boolean contains(V v);

    public boolean isEmpty();

    public boolean remove(V v);

    public int size();

    @Override
    public Iterator<V> iterator();
}
