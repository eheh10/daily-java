package com.v3.Operation;

import com.v3.tag.Count;
import com.v3.tag.Tag;

import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class Create implements Operation {
    private Count createFail;

    public Create(Count createFail){
        this.createFail = createFail;
    }

    @Override
    public void action(Queue<Tag> tags, Set<Tag> executableTags) {
        Tag tag = tags.poll();

        if(Objects.isNull(tag)){
            createFail.Counting();
        }

        executableTags.add(tag);
    }
}
