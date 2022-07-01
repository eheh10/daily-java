package test;

import java.io.*;

public class Test3_V3 {
    static class CharConverter {
        private final char value;

        public CharConverter(char value){
            this.value = value;
        }

        public CharConverter reverseConverter() {
            return new CharConverter(searchReverseCharacter());
        }

        private char searchReverseCharacter() {
            if (97 <= value && value <= 122) {
                return (char) (122 - value + 97);
            }
            if (65 <= value && value <= 90) {
                return (char) (90 - value + 65);
            }
            return value;
        }

        public char getValue() {
            return value;
        }
    }
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
            CharConverter charConverter = new CharConverter(c);
            answer.append(charConverter.reverseConverter().getValue());
        }
        
        return answer.toString();
    }
}
