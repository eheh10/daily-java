package com.v3.Operation;

import com.v3.tag.Tag;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Execute implements Operation{
    private final Tag EXECUTE_TAG;
    private Map<Tag,Integer> executeFail;

    public Execute(Tag executeTag, Map<Tag,Integer> executeFail){
        this.EXECUTE_TAG = executeTag;
        this.executeFail = executeFail;
    }

    @Override
    public void action(Queue<Tag> tags, Set<Tag> executableTags) {
        if(executableTags.contains(EXECUTE_TAG)){
            executableTags.remove(EXECUTE_TAG);
            tags.offer(EXECUTE_TAG);
        }else{
            executeFail.put(EXECUTE_TAG,executeFail.getOrDefault(EXECUTE_TAG,0)+1);
        }
    }
}
