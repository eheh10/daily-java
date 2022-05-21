package com.main;

import com.operation.Operation;
import com.operation.OperationFactory;
import com.tag.Tag;
import com.view.TagView;

import java.io.IOException;
import java.util.*;

public class Main {
    public static final String INPUT1 = "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "execute 2";
    public static final  String INPUT2 = "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "create";

    public static final String INPUT3 = "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "execute 11\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "create\n" +
            "execute 2\n" +
            "create\n" +
            "execute 2\n" +
            "execute 11\n" +
            "execute 2\n" +
            "execute 5\n" +
            "execute 5\n" +
            "execute 2\n" +
            "execute 5\n" +
            "execute 5";

    //1. 도메인 값 객체 (자료구조 연산 추상화)
//    public static final List<Tag> tags = new ArrayList<>();

    //2. 원시 데이터를 감싼 객체로 연산을 하게 만드는 것(Integer -> Tag)
//    public static Map<Integer,Integer> failTask = new HashMap<>();
//    public static int createFail = 0;

    public static void main(String[] args) throws IOException {
        Queue<Tag> tags = new PriorityQueue();
        Queue<Tag> executableTags = new PriorityQueue();

        Tag.initTag(tags);

//        InputStream is = System.in;
//        BufferedInputStream bis = new BufferedInputStream(is,100);
//        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
//        BufferedReader br = new BufferedReader(isr,100);
////
////        int n = Integer.parseInt(br.readLine());
//        while( n < 5 || n > 50 ){
//            System.out.println("N은 5 ≤ N ≤ 50");
//            n = Integer.parseInt(br.readLine());
//        }

        OperationFactory operationFactory = new OperationFactory();
        for(String command:INPUT1.split("\n")){

            Operation op = operationFactory.create(command);

            op.execute(tags, executableTags);
        }

        //3. 뷰 분리
        TagView.displayCreatableTags(tags);
        TagView.displayCreateFail();
        TagView.displayExecuteFail(Tag.getExecuteFailTags());
    }
}
