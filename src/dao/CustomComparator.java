package dao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CustomComparator<K,V extends Comparable> implements Comparator<K> {

    private final Map<K, V> map;

    public CustomComparator(Map<K, V> map) {
        this.map = new HashMap<>(map);
    }

    @Override
    public int compare(K s1, K s2) {
        return map.get(s1).compareTo(map.get(s2));
    }

}
