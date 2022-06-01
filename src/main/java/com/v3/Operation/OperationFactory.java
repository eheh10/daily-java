package com.v3.Operation;

import com.v3.tag.Count;
import com.v3.tag.Tag;

import java.util.Map;
import java.util.Objects;

public class OperationFactory {

    public Operation of(String cmd, Count createFail, Map<Tag,Integer> executeFail){
        String[] values = cmd.split(" ");

        if(Objects.equals(values[0],"create")){
            return new Create(createFail);
        }else if(values.length==2 && Objects.equals(values[0],"execute")){
            return new Execute(new Tag(values[1]),executeFail);
        }

        throw new RuntimeException();
    }

}
