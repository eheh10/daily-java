package com.view;

import com.tag.TaskComparator;
import com.tag.Tag;

import java.util.*;

public class TagView {

    public static void displayCreatableTags(Queue<Tag> tags){
        System.out.print("사용가능한 TAG: ");

        for(Tag t:tags){
            System.out.print(t.getNumber()+" ");
        }
        System.out.println();
    }

    public static void displayCreateFail(){
        System.out.println("TASK 생성 실패: "+Tag.getCreateFail());
    }

    public static void displayExecuteFail(Map<Tag,Object> executeFailTags){
        System.out.print("TASK 수행 실패한 태그: ");

        List<Map.Entry<Tag, Object>> failTasks = new ArrayList<>(executeFailTags.entrySet());
        Collections.sort(failTasks, new TaskComparator());

        Iterator<Map.Entry<Tag, Object>> failIt = failTasks.iterator();

        while(failIt.hasNext()){
            Map.Entry<Tag,Object> t = failIt.next();
            System.out.print(t.getKey().getNumber()+"("+t.getValue()+") ");
        }
    }
}
