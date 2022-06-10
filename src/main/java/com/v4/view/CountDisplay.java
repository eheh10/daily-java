package com.v4.view;

import com.v4.count.Count;

public class CountDisplay {
    private Count count;

    public CountDisplay(Count count){
        this.count = count;
    }

    public void display(){
        System.out.println("TASK 생성 실패: "+count.getCount());
    }
}
