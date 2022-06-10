package com.v4.operation;

import com.v4.tagPool.TagPool;
import com.v4.tagPool.Tags;

public interface Operation {
    void action(Tags tags, TagPool executableTags);
}
