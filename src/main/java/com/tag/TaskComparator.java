package com.tag;

import java.util.Comparator;
import java.util.Map;

public class TaskComparator implements Comparator<Map.Entry<Tag, Object>> { //범용적으로 사용해야한다 -> fail,success 은 사용처에서 결정
    @Override
    public int compare(Map.Entry<Tag,Object> o1, Map.Entry<Tag,Object> o2) {
        int cnt1 = (Integer)o1.getValue();
        int cnt2 = (Integer)o2.getValue();

        if(cnt1 == cnt2){
            return o1.getKey().compareTo(o2.getKey());
        }

        return cnt2-cnt1;
    }
}
