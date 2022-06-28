package test;

import java.io.*;

public class Test3_V2 {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis),8192);

        String word = br.readLine();
        System.out.println(solution(word));
    }

    public static String solution(String word) {
        StringBuilder answer = new StringBuilder();

        for(char c:word.toCharArray()){
            answer.append(reverseConverter(c));
        }

        return answer.toString();
    }

    private static char reverseConverter(char c) {
        if( 97 <= c && c <= 122 ){
            return (char)(122-c+97);
        }else if( 65 <= c && c <= 90 ) {
            return (char) (90 - c + 65);
        }
        return c;
    }
}
