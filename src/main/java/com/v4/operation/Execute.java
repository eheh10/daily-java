package com.v4.operation;

import com.v4.tag.Tag;
import com.v4.tagPool.TagCount;
import com.v4.tagPool.TagPool;
import com.v4.tagPool.Tags;

public class Execute implements Operation{
    private Tag executeTag;
    private TagCount executeFail;

    public Execute(Tag executeTag, TagCount executeFail) {
        this.executeTag = executeTag;
        this.executeFail = executeFail;
    }

    @Override
    public void action(Tags tags, TagPool executableTags) {
        if(executableTags.contains(executeTag)){
            executableTags.remove(executeTag);
            tags.add(executeTag);

            return;
        }

        executeFail.put(executeTag, executeFail.getOrDefault(executeTag,0)+1);
    }
}
