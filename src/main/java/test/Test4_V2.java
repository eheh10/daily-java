package test;

import java.io.*;

public class Test4_V2 {

    static class Page{
        private final int value;

        public Page(int value){
            this.value = value;
        }

        public Page sumEachDigit(){
            int sumValue = 0;
            int remain = value;
            for( ; remain > 0; remain/=10){
                sumValue += remain%10;
            }

            return new Page(sumValue);
        }

        public Page multiplyEachDigit(){
            int multiplyValue = 1;
            int remain = value;
            for( ; remain > 0; remain/=10){
                multiplyValue *= remain%10;
            }

            return new Page(multiplyValue);
        }

        public int compare(Page page) {
            if (this.equals(page)){
                return 0;
            }

            if (value - page.value > 0){
                return 1;
            }

            return -1;
        }

        public boolean isNotNextPage(Page page) {
            return value - page.value != -1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Page page = (Page) o;

            return value == page.value;
        }

        @Override
        public int hashCode() {
            return value;
        }
    }

    static class Book {
        private final Page leftPage;
        private final Page rightPage;

        public Book(Page leftPage, Page rightPage) {
            this.leftPage = leftPage;
            this.rightPage = rightPage;
        }

        public static Book from(int left, int right){
            return new Book(new Page(left), new Page(right));
        }

        public boolean isNotValid(){
            if (leftPage.isNotNextPage(rightPage)){
                return true;
            }
            return false;
        }

        private Page CalculateMaxPage(){
            Page leftMax = leftPage.sumEachDigit();
            Page rightMax = rightPage.sumEachDigit();

            if (leftMax.compare(leftPage.multiplyEachDigit()) == -1){
                leftMax = leftPage.multiplyEachDigit();
            }
            if (rightMax.compare(rightPage.multiplyEachDigit()) == -1){
                rightMax = rightPage.multiplyEachDigit();
            }

            if(leftMax.compare(rightMax) == 1){
                return leftMax;
            }
            return rightMax;
        }

        public int compare(Book book){
            Page maxPage = CalculateMaxPage();
            Page bookMaxPage = book.CalculateMaxPage();

            return maxPage.compare(bookMaxPage);
        }
    }

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
        Book pobiBook = Book.from(pobi[0],pobi[1]);
        Book crongBook = Book.from(crong[0],crong[1]);

        if (pobiBook.isNotValid() || crongBook.isNotValid()){
            return -1;
        }

        if (pobiBook.compare(crongBook) == 0){
            return 0;
        }

        if (pobiBook.compare(crongBook) == 1){
            return 1;
        }

        return 2;
    }

}
