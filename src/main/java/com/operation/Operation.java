package com.operation;

import com.tag.Tag;

import java.util.Queue;

public interface Operation {
    void execute(Queue<Tag> tags, Queue<Tag> executableTags);
}
