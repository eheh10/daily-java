package com.v4.main;

import com.v4.count.Count;
import com.v4.operation.Operation;
import com.v4.operation.OperationFactory;
import com.v4.tag.Tag;
import com.v4.tagPool.TagCount;
import com.v4.tagPool.TagPool;
import com.v4.tagPool.Tags;
import com.v4.view.CountDisplay;
import com.v4.view.TagCountDisplay;
import com.v4.view.TagsDisplay;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr,8192);

        int N = 0;
        Tags tags = new Tags(getDefaultTags());
        TagPool executableTags = new TagPool(new HashSet<>());
        Count createFail = new Count();
        TagCount executeFail = new TagCount(new HashMap<>());

        System.out.print("N (5<=N<=50) : ");
        while(N<=5 || N >=50) {
            N = Integer.parseInt(br.readLine());
        }

        OperationFactory operationFactory = new OperationFactory();

        for(int i=0; i<N; i++){
            String op = br.readLine();

            Operation operation = operationFactory.of(op, createFail, executeFail);
            operation.action(tags, executableTags);
        }

        displayResult(tags,createFail,executeFail);

    }

    private static void displayResult(Tags tags, Count createFail, TagCount executeFail) {
        new TagsDisplay(tags).display();
        new CountDisplay(createFail).display();
        new TagCountDisplay(executeFail).display();
    }


    private static Queue<Tag> getDefaultTags() {
        Queue<Tag> tags = new PriorityQueue<>();

        return Tag.initTag(tags);
    }
}
