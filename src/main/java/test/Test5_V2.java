package test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test5_V2 {
    static class Group{
        private final List<Integer> elements;

        public Group(List<Integer> elements) {
            this.elements = elements;
        }

        public static Group from(int num){
            List<Integer> elements = new ArrayList<>();

            for( ; num>0; num/=10){
                elements.add(num%10);
            }

            return new Group(elements);
        }

        public boolean contains(Group group){
            for(int i : group.elements) {
                if (elements.contains(i)) {
                    return true;
                }
            }
            return false;
        }
    }
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis),8192);

        int number = Integer.parseInt(br.readLine());

        System.out.println(solution(number));
    }

    public static int solution(int number) {
        int answer = 0;
        Group target = new Group(new ArrayList<>(List.of(3,6,9)));

        for(int i=1; i<=number; i++){
            Group num = Group.from(i);
            if (num.contains(target)){
                answer++;
                continue;
            }
        }


        return answer;
    }
}
