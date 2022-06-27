package test;

import java.io.*;

public class Test5 {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis),8192);

        int number = Integer.parseInt(br.readLine());

        System.out.println(solution(number));
    }

    public static int solution(int number) {
        int answer = 0;

        for(int i=1; i<=number; i++){
            String num = String.valueOf(i);
            if (num.indexOf('3')>-1 || num.indexOf('6')>-1 || num.indexOf('9')>-1) {
                answer++;
            }
        }
        return answer;
    }
}
