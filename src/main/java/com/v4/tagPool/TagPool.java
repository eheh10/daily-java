package com.v4.tagPool;

import com.v4.tag.Tag;

import java.util.Set;

public class TagPool {
    private Set<Tag> tagPool;

    public TagPool(Set<Tag> tagPool) {
        this.tagPool = tagPool;
    }

    public void add(Tag t) {
        tagPool.add(t);
    }

    public boolean contains(Tag tag) {
        return tagPool.contains(tag);
    }

    public void remove(Tag tag) {
        tagPool.remove(tag);
    }

}
