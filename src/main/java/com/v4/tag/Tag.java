package com.v4.tag;

import java.util.Queue;

public class Tag implements Comparable<Tag>{
    private int num;

    public Tag(String num) {
        this.num = Integer.parseInt(num);
    }

    public static Queue<Tag> initTag(Queue<Tag> tags){
        for(int i=1; i<=9; i++){
            tags.add(new Tag(i+""));
        }

        return tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return num == tag.num;
    }

    @Override
    public int hashCode() {
        return num;
    }


    @Override
    public int compareTo(Tag o) {
        return num - o.num;
    }

    @Override
    public String toString() {
        return num+"";
    }
}
