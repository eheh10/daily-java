package com.v3.tag;

import java.util.Comparator;
import java.util.Map;

public class TaskComparator implements Comparator<Map.Entry<Tag, Integer>> {

    @Override
    public int compare(Map.Entry<Tag, Integer> o1, Map.Entry<Tag, Integer> o2) {
        if(o1.getValue() == o2.getValue()){
            return Integer.compare(Integer.parseInt(o1.getKey().toString()),Integer.parseInt(o2.getKey().toString()));
        }
        return o2.getValue()-o1.getValue();
    }
}
