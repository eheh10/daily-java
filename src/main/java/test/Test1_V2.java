package test;

import java.io.*;

public class Test1_V2 {
    class CoinPool {
        private final int[] coins;

        public CoinPool(int[] coins){
            this.coins = coins;
        }

        public int get(int index){
            return coins[index];
        }

        public int getLength(){
            return coins.length;
        }

    }

    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis),8192);

        int money = Integer.parseInt(br.readLine());

        Test1_V2 t =  new Test1_V2();
        t.displayResult(t.solution(money));
    }

    private int[] solution(int money) {
        CoinPool coin = new CoinPool(getDefaultCoin());
        int[] answers = new int[coin.getLength()];

        for(int i=0; i<coin.getLength(); i++){
            int n = money/coin.get(i);

            if(n > 0){
                answers[i] = n;
                money %= coin.get(i);
                continue;
            }
        }

        return answers;
    }

    private static int[] getDefaultCoin(){
        return new int[] {50000,10000,5000,1000,500,100,50,10,1};
    }
    private void displayResult(int[] solution) {
        StringBuilder result = new StringBuilder();

        result.append("[");

        for(int n:solution){
            result.append(n).append(",").append(" ");
        }

        result.setLength(result.length()-2);
        result.append("]");

        System.out.println(result.toString());
    }
}
