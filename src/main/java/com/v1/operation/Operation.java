package com.v1.operation;

import com.v1.tag.Tag;

import java.util.Map;
import java.util.Queue;

public interface Operation {
    void execute(Queue<Tag> tags, Queue<Tag> executableTags, Map<Tag,Object> executeFailTags);
}
