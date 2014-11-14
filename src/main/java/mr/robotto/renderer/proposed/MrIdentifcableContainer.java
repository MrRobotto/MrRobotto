package mr.robotto.renderer.proposed;

import java.util.HashMap;
import java.util.Iterator;

public class MrIdentifcableContainer<K,V extends MrIdentificable<K>> implements MrContainer<K,V> {

    private HashMap<K, V> mElements;

    public MrIdentifcableContainer() {
        mElements = new HashMap<K, V>();
    }

    @Override
    public boolean add(V v) {
        return mElements.put(v.getElementId(), v) != null;
    }

    @Override
    public void clear() {
        mElements.clear();
    }

    @Override
    public boolean containsKey(K k) {
        return mElements.containsKey(k);
    }

    @Override
    public boolean containsValue(V v) {
        return mElements.containsKey(v.getElementId());
    }

    @Override
    public V get(K k) {
        return mElements.get(k);
    }

    @Override
    public boolean isEmpty() {
        return mElements.isEmpty();
    }

    @Override
    public Iterator<V> iterator() {
        return mElements.values().iterator();
    }

    @Override
    public boolean removeByKey(K k) {
        return mElements.remove(k) != null;
    }

    @Override
    public boolean removeValue(V v) {
        return removeByKey(v.getElementId());
    }

    @Override
    public int size() {
        return mElements.size();
    }

    @Override
    public String toString() {
        String s = "";
        for (V v : mElements.values()) {
            s += v.getElementId().toString();
        }
        return s;
    }
}
