package test;

import java.io.*;

public class Test7 {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis),8192);

        String cryptogram = br.readLine();

        System.out.println(solution(cryptogram));
    }

    public static String solution(String cryptogram) {
        StringBuilder decode = new StringBuilder(cryptogram);

        for(int i=decode.length()-1; i>=1; i--){
            if (decode.charAt(i)==decode.charAt(i-1)){
                decode.delete(i-1,i+1);
            }
        }

        return decode.toString();
    }
}
