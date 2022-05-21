package com.tag;

import java.util.Objects;
import java.util.Queue;

//enum Status {
//    READY, CREATED,
//}

public class Tag implements Comparable<Tag>{
    private final String number;
//    public int executeFail = 0; //public 은 set도 가능 -> getter 제공
//    private Status status = Status.READY; //사실상 setter, getter
    private static int createFail;

    public Tag(String number){
        this.number = number;
    }

    public String getNumber(){
        return number;
    }

    public static int getCreateFail(){
        return createFail;
    }

    public static void createFailCount() {
        createFail++;
    }

    public static void initTag(Queue<Tag> tags){
        for(int i=1; i<=9; i++){
            tags.offer(new Tag(i+""));
        }
    }

    @Override
    public boolean equals(Object o) { //number가 같으면 같은 Tag
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(number, tag.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(Tag o) {
        return number.compareTo(o.number);
    }
}
