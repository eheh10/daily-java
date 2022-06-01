package com.v3.tag;

import java.util.Objects;
import java.util.Queue;

public class Tag implements Comparable<Tag>{
    private final String NUMBER;

    public Tag(String NUMBER){
        this.NUMBER = NUMBER;
    }

    public static void initTag(Queue<Tag> tags){
        for(int i=1; i<=9; i++){
            tags.offer(new Tag(i+""));
        }
    }

    @Override
    public String toString() {
        return NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return Objects.equals(NUMBER, tag.NUMBER);
    }

    @Override
    public int hashCode() {
        return NUMBER != null ? NUMBER.hashCode() : 0;
    }

    @Override
    public int compareTo(Tag o) {
        return Integer.parseInt(this.NUMBER)-Integer.parseInt(o.NUMBER);
    }
}
