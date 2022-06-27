package test;

import java.io.*;

public class Test4 {
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

        int pMax = 0;
        int cMax = 0;

        for(int i=0; i<2; i++){
            int pValue = pobi[i];
            int psum = 0;
            int pmul = 1;
            for(int n=10; pValue > 0 ; pValue /= n){
                psum += pValue%n;
                pmul *= pValue%n;

            }

            int cValue = crong[i];
            int csum = 0;
            int cmul = 1;
            for(int n=10; cValue > 0 ; cValue /= n){
                csum += cValue%n;
                cmul *= cValue%n;

            }

            pMax = Math.max(pMax, Math.max(psum,pmul));
            cMax = Math.max(cMax, Math.max(csum,cmul));
        }

        if (pMax == cMax){
            return 0;
        }else if(pMax > cMax){
            return 1;
        }
        return 2;
    }
}
