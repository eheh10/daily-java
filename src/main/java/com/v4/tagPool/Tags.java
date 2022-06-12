package com.v4.tagPool;

import com.v4.tag.Tag;

import java.util.Queue;

public class Tags {
    private Queue<Tag> tags;

    public Tags(Queue<Tag> tags) {
        this.tags = tags;
    }

    public boolean hasNext() {
        return !tags.isEmpty();
    }

    public Tag poll() {
        return tags.poll();
    }

    public void add(Tag tag) {
        tags.add(tag);
    }
}
