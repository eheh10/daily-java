package com.v4.operation;

import com.v4.count.Count;
import com.v4.tag.Tag;
import com.v4.tagPool.TagPool;
import com.v4.tagPool.Tags;

public class Create implements Operation{
    private Count createFail;

    public Create(Count createFail) {
        this.createFail = createFail;
    }

    @Override
    public void action(Tags tags, TagPool executableTags) {
        if (!tags.isEmpty()){
            Tag t = tags.poll();
            executableTags.add(t);
            return;
        }

        createFail.increase();
    }
}
