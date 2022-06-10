package com.v4.view;

import com.v4.tag.Tag;
import com.v4.tag.TagComparator;
import com.v4.tagPool.TagCount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TagCountDisplay {
    private TagCount executeFail;

    public TagCountDisplay(TagCount executeFail) {
        this.executeFail = executeFail;
    }

    public void display(){
        System.out.print("TASK 수행 실패한 태그: ");

        List<Map.Entry<Tag, Integer>> list = new ArrayList(executeFail.entrySet());
        Collections.sort(list,new TagComparator());

        for(int i=0; i<list.size(); i++){
            Map.Entry<Tag,Integer> m = list.get(i);
            System.out.print(m.getKey()+"("+m.getValue()+")"+" ");
        }
        System.out.println();

    }
}
