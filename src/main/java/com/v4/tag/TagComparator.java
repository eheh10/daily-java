package com.v4.tag;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class TagComparator implements Comparator<Map.Entry<Tag,Integer>> {
    @Override
    public int compare(Map.Entry<Tag, Integer> o1, Map.Entry<Tag, Integer> o2) {
        if(Objects.equals(o1.getValue(),o2.getValue())){ //Integer는 비교연산자 x
            return o2.getValue() - o1.getValue();
        }
        return Integer.parseInt(o1.getKey().toString()) - Integer.parseInt(o2.getKey().toString());
    }
}
