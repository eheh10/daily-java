package test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test7_V2 {
    class CharPool{
        private List<Character> characters;

        public CharPool(String characters){
            this.characters = defaultCryptogram(characters);
        }

        public int size(){
            return characters.size();
        }

        public Character get(int index){
            return characters.get(index);
        }

        public void remove(int index){
            characters.remove(index);
        }

        private List<Character> defaultCryptogram(String cryptogram) {
            List<Character> initial = new ArrayList<>();

            for(char c:cryptogram.toCharArray()){
                initial.add(c);
            }

            return initial;
        }

    }

    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis),8192);

        String cryptogram = br.readLine();

        System.out.println(new Test7_V2().solution(cryptogram));
    }

    public String solution(String cryptogram) {
        StringBuilder answer = new StringBuilder();
        CharPool decode = decoding(cryptogram);

        for(int i=0; i<decode.size(); i++){
            answer.append(decode.get(i));
        }

        return answer.toString();
    }

    private CharPool decoding(String cryptogram) {
        CharPool decode = new CharPool(cryptogram);

        for(int i=decode.size()-1; i>=1; i--){
            if(decode.get(i) == decode.get(i-1)){
                decode.remove(i);
                decode.remove(i-1);
            }
        }
        return decode;
    }
}
