package com.operation;

import com.main.Main;
import com.tag.Tag;

import java.util.Objects;

public class ExecuteOperation implements Operation {
    private Tag executeTag;

    public ExecuteOperation(String number){
        executeTag = new Tag(number);
    }

    @Override
    public void execute() {
        if(tagNumber-1 < Main.tags.size()) {
            Tag tag = Main.tags.get(tagNumber - 1);
            if (tag.isCreated()) {
                tag.execute();
                return;
            }
        }

        if (Main.failTask.containsKey(tagNumber)) {
            Main.failTask.put(tagNumber, Main.failTask.get(tagNumber)+1);
        }else {
            Main.failTask.put(tagNumber, 1);
        }
    }
}
