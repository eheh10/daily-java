package com.operation;

public interface Operation {
    void execute(Queue<Tag> tags, Queue<Tag> executableTags);
}
