package com.tag;

enum Status {
    READY, CREATED,
}

public class Tag {
    private final int number;
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

    public boolean isReady(){
        return status==Status.READY;
    }

    public boolean isCreated(){
        return status==Status.CREATED;
    }
}
