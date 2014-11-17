package mr.robotto.renderer.proposed.containers;

public interface MrMap<K, V> {
    public boolean removeByKey(K k);

    public boolean containsKey(K k);

    public boolean put(K k, V v);

    public V find(K k);
}
