package com.tag;

import java.util.Comparator;

public class TagComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Tag && o2 instanceof Tag){
            Tag t1 = (Tag) o1;
            Tag t2 = (Tag) o2;

            if(t1.executeFail == t2.executeFail){
                return t1.number - t2.number;
            }

            return t2.executeFail - t1.executeFail;
        }
        return -1;
    }
}
