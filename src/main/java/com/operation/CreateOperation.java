package com.operation;

import com.tag.Tag;

import java.util.Map;
import java.util.Queue;

public class CreateOperation implements Operation{

    @Override
    public void execute(Queue<Tag> tags, Queue<Tag> executableTags, Map<Tag,Object> executeFailTags) {
        boolean isCreated = false;
        Tag t = tags.poll();
        if(t!=null) {
            isCreated = executableTags.offer(t);
        }

        if(!isCreated){
            Tag.createFailCount();
        }
    }
}
