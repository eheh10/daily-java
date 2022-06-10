package com.v4.operation;

import com.v4.count.Count;
import com.v4.tag.Tag;
import com.v4.tagPool.TagCount;

import java.util.Objects;

public class OperationFactory {
    public Operation of(String op, Count createFail, TagCount executeFail){
        String[] values = op.split(" ");

        if (Objects.equals(values[0],"create")){
            return new Create(createFail);
        }else if (Objects.equals(values[0],"execute") && values.length==2){
            return new Execute(new Tag(values[1]), executeFail);
        }

        throw new RuntimeException("잘못된 명령어");
    }
}
