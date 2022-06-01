package com.v3.Operation;

import com.v3.tag.Tag;

import java.util.Queue;
import java.util.Set;

public interface Operation {
    void action(Queue<Tag> tags, Set<Tag> executableTags);
}
