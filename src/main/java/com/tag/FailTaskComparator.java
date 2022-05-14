package com.tag;

import java.util.Comparator;
import java.util.Map;

public class FailTaskComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Map.Entry && o2 instanceof Map.Entry){
            Map.Entry t1 = (Map.Entry) o1;
            Map.Entry t2 = (Map.Entry) o2;

            if(t1.getValue() == t2.getValue()){
                return ((Integer)t1.getKey()).intValue() - ((Integer)t2.getKey()).intValue();
            }

            return ((Integer)t2.getValue()).intValue() - ((Integer)t1.getValue()).intValue();
        }
        return -1;
    }
}
