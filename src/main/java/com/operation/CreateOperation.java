package com.operation;

import com.main.Main;
import com.tag.Tag;

import java.util.Objects;

public class CreateOperation implements Operation{
    @Override
    public boolean isSupport(String cmd) {
        return Objects.equals(cmd.split(" ")[0],"create");
    }

    @Override
    public void execute() {
        for(Tag t: Main.tags){
            if(t.isReady()){
                t.create();
                return;
            }
        }

        Main.createFail++;
    }
}
