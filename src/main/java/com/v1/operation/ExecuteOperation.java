package com.v1.operation;

import com.v1.tag.Tag;

import java.util.Map;
import java.util.Queue;

public class ExecuteOperation implements Operation {
    private Tag executeTag;

    public ExecuteOperation(String number){
        executeTag = new Tag(number);
    }

    @Override
    public void execute(Queue<Tag> tags, Queue<Tag> executableTags, Map<Tag,Object> executeFailTags) {
        boolean isExecuted = executableTags.remove(executeTag);

        if(!isExecuted){
            int cnt = (Integer)executeFailTags.getOrDefault(executeTag,0) + 1;

            executeFailTags.put(executeTag,cnt);
            return;
        }

        tags.offer(executeTag);
    }
}
