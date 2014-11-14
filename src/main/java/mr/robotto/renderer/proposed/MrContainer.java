package mr.robotto.renderer.proposed;

import java.util.Iterator;

public interface MrContainer<K, V> extends Iterable<V> {

    boolean add(V v);

    void clear();

    boolean containsKey(K k);

    boolean containsValue(V v);

    V get(K k);

    boolean isEmpty();

    @Override
    Iterator<V> iterator();

    boolean removeByKey(K k);

    boolean removeValue(V v);

    int size();
}
