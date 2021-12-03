package experiments.datastructure.hashset;

import experiments.datastructure.hashmap.SimpleHashMap;

public final class SimpleHashSet<E> {

    private static final Object PRESENT = new Object();

    private final SimpleHashMap<E, Object> map;

    public SimpleHashSet() {
        this.map = new SimpleHashMap<>();
    }

    public void add(E element) {
        map.put(element, PRESENT);
    }

    public boolean remove(E element) {
        return map.remove(element);
    }

    public boolean contains(E element) {
        return map.get(element) != null;
    }
}
