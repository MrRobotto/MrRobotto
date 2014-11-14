package mr.robotto.renderer.proposed;

public class MrIdentificableNode<K, V extends MrIdentificable<K>> extends MrNode<V> implements MrContainer<K,V> {
    public MrIdentificableNode(MrNode<V> parent, V data) {
        super(parent, data);
    }

    @Override
    public boolean add(V v) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K k) {
        return false;
    }

    @Override
    public boolean containsValue(V v) {
        return false;
    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean removeByKey(K k) {
        return false;
    }

    @Override
    public boolean removeValue(V v) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
