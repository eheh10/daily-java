package com.operation;

import com.main.Main;
import com.tag.Tag;

import java.util.Objects;

public class ExecuteOperation implements Operation {
    private int tagNumber;

    @Override
    public boolean isSupport(String cmd) {
        String[] metas = cmd.split(" ");

        if (Objects.equals(metas[0],"execute")) {
            tagNumber = Integer.parseInt(metas[1]);
            return true;
        }

        return false;
    }

    @Override
    public void execute() {
        Tag tag = Main.tags.get(tagNumber+1);
        if(tag.isCreated()){
            tag.execute();
        }else{
            tag.countExecuteFail();
        }
    }
}
