package test;

import java.io.*;

public class Test3 {
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
            if(Character.isAlphabetic(c)){
                if(Character.isLowerCase(c)){
                    answer.append((char)(122-c+97));
                    continue;
                }
                answer.append((char)(90-c+65));
                continue;
            }
            answer.append(c);
        }

        return answer.toString();
    }
}
