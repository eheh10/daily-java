package com.v1.operation;

import java.util.Objects;

public class OperationFactory {
    public Operation create(String cmd){
        String[] metas = cmd.split(" ");

        if (Objects.equals(metas[0],"execute")) {
            return new ExecuteOperation(metas[1]);
        }else if(Objects.equals(metas[0],"create")){
            return new CreateOperation();
        }

        throw new RuntimeException();
    }
}
