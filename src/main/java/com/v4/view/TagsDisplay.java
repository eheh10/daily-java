package com.v4.view;

import com.v4.tagPool.Tags;

public class TagsDisplay {
    private Tags tags;

    public TagsDisplay(Tags tags) {
        this.tags = tags;
    }

    public void display(){
        System.out.print("사용가능한 TAG: ");
        while(!tags.hasNext()){
            System.out.print(tags.poll()+" ");
        }
        System.out.println();
    }
}
