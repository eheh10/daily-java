package test;

import java.io.*;

public class Test5_V2 {
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
            answer += calculate(i);
        }
        return answer;
    }

    private static int calculate(int n) {
        final int divide = 10;

        while(n > 0){
            int value = n%divide;

            if(value==3 || value==6 || value==9){
                return 1;
            }

            n/=divide;
        }

        return 0;
    }
}
