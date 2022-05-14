package com.tag;

enum Status {
    READY, CREATED,
}

public class Tag {
    private final int number;
    private int createFail = 0;
    private int executeFail = 0;
    private Status status = Status.READY;

    public Tag(int number){
        this.number = number;
    }

    public void create(){
        status = Status.CREATED;
    }

    public void execute(){
        status = Status.READY;
    }

    public void countCreateFail(){
        createFail++;
    }

    public void countExecuteFail(){
        executeFail++;
    }
}
