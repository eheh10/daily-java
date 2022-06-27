package test;

import java.io.*;
import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis),8192);

        int money = Integer.parseInt(br.readLine());

        System.out.println(Arrays.toString(solution(money)));
    }

    public static int[] solution(int money) {
        int[] coin = {50000,10000,5000,1000,500,100,50,10,1};
        int[] answers = new int[coin.length];

        for(int i=0; i<coin.length; i++){
            int n = money/coin[i];

            if(n > 0){
                answers[i] = n;
                money %= coin[i];
                continue;
            }
        }

        return answers;
    }

}
