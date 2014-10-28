package mr.robotto.renderer.proposed;

import java.util.HashMap;
import java.util.Iterator;

public abstract class MrContainer<K,V extends MrIdentificable<K>> implements Iterable<V> {

    private HashMap<K, V> mElements;

    public MrContainer() {
        mElements = new HashMap<K, V>();
    }

    public boolean add(V v) {
        return mElements.put(v.getElementId(), v) != null;
    }

    public void clear() {
        mElements.clear();
    }

    public boolean contains(K k) {
        return mElements.containsKey(k);
    }

    public boolean contains(V v) {
        return mElements.containsKey(v.getElementId());
    }

    public V get(K k) {
        return mElements.get(k);
    }

    public boolean isEmpty() {
        return mElements.isEmpty();
    }

    @Override
    public Iterator<V> iterator() {
        return mElements.values().iterator();
    }

    public boolean remove(K k) {
        return mElements.remove(k) != null;
    }

    public boolean remove(V v) {
        return remove(v.getElementId());
    }

    public int size() {
        return mElements.size();
    }
}
