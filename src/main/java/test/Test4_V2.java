package test;

import java.io.*;

public class Test4_V2 {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis),8192);

        int[] pobi = new int[2];
        String[] input = br.readLine().split(",");

        for(int i=0; i<pobi.length; i++){
            pobi[i] = Integer.parseInt(input[i]);
        }

        int[] crong = new int[2];
        input = br.readLine().split(",");

        for(int i=0; i<crong.length; i++){
            crong[i] = Integer.parseInt(input[i]);
        }

        System.out.println(solution(pobi,crong));
    }

    public static int solution(int[] pobi, int[] crong) {
        if (pobi[0]+1 != pobi[1] || crong[0]+1 != crong[1]){
            return -1;
        }

        int pMax = maxNum(pobi);
        int cMax = maxNum(crong);

        if (pMax == cMax){
            return 0;
        }else if(pMax > cMax){
            return 1;
        }
        return 2;
    }

    private static int maxNum(int[] arr) {
        int max = Integer.MIN_VALUE;

        for(int i=0; i<2; i++){
            int value = arr[i];
            int sum = 0;
            int mul = 1;

            for(int n=10; value > 0 ; value /= n){
                int num = value%n;
                sum += num;
                mul *= num;
            }

            if (max < sum){
                max = sum;
            }

            if (max < mul){
                max = mul;
            }
        }

        return max;
    }
}
