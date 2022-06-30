package test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test3_V2 {
    static class Word{

        private final List<Character> values;

        public Word(List<Character> values){
            this.values = values;
        }

        public static Word from(String value){ //사용처 편의 제공
            List<Character> values = new ArrayList<>();
            for(char c:value.toCharArray()){
                values.add(c);
            }

            return new Word(values);
        }

        public Word reverseConverter() {
            List<Character> reverseValues = new ArrayList<>();

            for(int i=0; i<values.size(); i++) {
                reverseValues.add(searchReverseCharacter(values.get(i)));
            }

            return new Word(reverseValues);
        }

        private Character searchReverseCharacter(Character c) {
            if (97 <= c && c <= 122) {
                return (char) (122 - c + 97);
            }
            if (65 <= c && c <= 90) {
                return (char) (90 - c + 65);
            }
            return c;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            for(char c:values){
                str.append(c);
            }
            return str.toString();
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
        Word originalWord = Word.from(word);
        Word reverseWord = originalWord.reverseConverter();

        return reverseWord.toString();
    }
}
