package com.v3.main;

import com.v3.Operation.Operation;
import com.v3.Operation.OperationFactory;
import com.v3.tag.Count;
import com.v3.tag.Tag;
import com.v3.view.TagView;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Queue<Tag> tags = new PriorityQueue<>();
        Set<Tag> executableTags = new HashSet<>();
        Count createFail = new Count();
        Map<Tag,Integer> executeFail = new HashMap<>();

        Tag.initTag(tags);

        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr,8192);

        System.out.print("연산 개수 N입력 (N의 범위는 5<=N<=50) : ");
        int n = Integer.parseInt(br.readLine());

        while(n<5 || n>50){
            System.out.print("N 재입력 (5<=N<=50) : ");
        }

        for(int i=0; i<n; i++){
            String cmd = br.readLine();

            OperationFactory operationFactory = new OperationFactory();
            Operation operation = operationFactory.of(cmd,createFail,executeFail);

            operation.action(tags, executableTags);
        }

        TagView tagView = new TagView(tags,createFail,executeFail);
        tagView.displayResult();

    }
}
