package test;

import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Test1_V2 {
    class Coin implements Comparable<Coin>{
        private final int coin;

        public Coin(int coin) {
            this.coin = coin;
        }

        public int changeCoin(Money money){
            return money.getMoney()/coin;
        }

        @Override
        public int compareTo(Coin o) {
            return o.coin - coin;
        }

        public int getCoin() {
            return coin;
        }
    }

    class Money{
        private final int money;

        public Money(int money) {
            this.money = money;
        }

        public int getMoney() {
            return money;
        }

        public int change(Coin coin, int cnt){
            return money - coin.getCoin() * cnt;
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
        Set<Coin> coins = getDefaultCoins();
        Money tmp = new Money(money);

        int[] answers = new int[coins.size()];
        int i = 0;

        Iterator<Coin> it = coins.iterator();

        while(it.hasNext()){
            Coin coin = it.next();
            int n = coin.changeCoin(tmp);

            answers[i++] = n;
            tmp = new Money(tmp.change(coin, n));
        }

        return answers;
    }

    private Set<Coin> getDefaultCoins(){
        Set<Coin> coins = new TreeSet<>();

        coins.add(new Coin(50000));
        coins.add(new Coin(10000));
        coins.add(new Coin(5000));
        coins.add(new Coin(1000));
        coins.add(new Coin(500));
        coins.add(new Coin(100));
        coins.add(new Coin(50));
        coins.add(new Coin(10));
        coins.add(new Coin(5));
        coins.add(new Coin(1));

        return coins;
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
