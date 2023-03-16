package dao;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class ComparatorLabWorkTreeMap {

    public static TreeMap<Integer, Integer> sortByValues(Map<Integer, Integer> map)
    {
        Comparator<Integer> comparator = new CustomComparator<>(map);

        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(comparator);
        sortedMap.putAll(map);

        return sortedMap;
    }

}