package com.main;

import com.operation.CreateOperation;
import com.operation.ExecuteOperation;
import com.operation.Operation;
import com.tag.Tag;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static final List<Tag> tags = new ArrayList<>();
    public static int createFail = 0;

    public static void main(String[] args) throws IOException {
        for(int i=1; i<=9; i++){
            tags.add(new Tag(i));
        }

        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,100);
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr,100);

        int n = Integer.parseInt(br.readLine());
        while( n < 5 || n > 50 ){
            System.out.println("N은 5 ≤ N ≤ 50");
            n = Integer.parseInt(br.readLine());
        }

        for(int i=0; i < n ; i++){
            String command = br.readLine();

            Operation op = Stream.of(new CreateOperation(), new ExecuteOperation())
                    .filter(o->o.isSupport(command))
                    .findFirst()
                    .orElseThrow();

            op.execute();
        }
    }
}
