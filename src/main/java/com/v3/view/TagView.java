package com.v3.view;

import com.v3.tag.Count;
import com.v3.tag.Tag;
import com.v3.tag.TaskComparator;

import java.util.*;

public class TagView {
    private Queue<Tag> tags;
    private Count createFail;
    private Map<Tag,Integer> executeFail;

    public TagView(Queue<Tag> tags, Count createFail, Map<Tag,Integer> executeFail){
        this.tags = tags;
        this.createFail = createFail;
        this.executeFail = executeFail;
    }

    public void displayResult(){
        displayTags();
        displayCreateFail();
        displayExecuteFail();
    }

    private void displayTags(){
        System.out.print("사용가능한 Tag: ");
        while(!tags.isEmpty()){
            System.out.print(tags.poll().toString()+" ");
        }
        System.out.println();
    }

    private void displayCreateFail(){
        System.out.println("TASK 생성 실패: "+createFail.getCount());
    }

    private void displayExecuteFail(){
        List<Map.Entry<Tag, Integer>> fails = new ArrayList<>(executeFail.entrySet());
        Collections.sort(fails, new TaskComparator());

        System.out.print("TASK 수행 실패한 태그: ");
        for(Map.Entry<Tag,Integer> f:fails){
            System.out.print(f.getKey()+"("+f.getValue()+")" +" ");
        }
        System.out.println();
    }
}
