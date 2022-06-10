package com.v4.tagPool;

import com.v4.tag.Tag;

import java.util.Map;
import java.util.Set;

public class TagCount {
    private Map<Tag,Integer> map;

    public TagCount(Map<Tag, Integer> map) {
        this.map = map;
    }

    public void put(Tag tag, int n){
        map.put(tag, n);
    }

    public int getOrDefault(Tag tag, int n) {
        return map.getOrDefault(tag,n);
    }

    public Set<Map.Entry<Tag,Integer>> entrySet() {
        return map.entrySet();
    }
}
