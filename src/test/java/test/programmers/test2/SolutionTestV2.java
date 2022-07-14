package test.programmers.test2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTestV2 {
    static class NumberSet{
        private final List<Integer> values;

        public NumberSet(List<Integer> values) {
            this.values = values;
        }

        public static NumberSet from(int size){
            List<Integer> values = new ArrayList<>(Math.max(10,size));
            return new NumberSet(values);
        }

        public void add(int num){
            if (values.size() == 0) {
                values.add(num);
                return;
            }

            if (values.get(values.size()-1) == num){
                remove(values.size()-1);
                return;
            }

            values.add(num);
        }

        private void remove(int index){
            values.remove(index);
        }

        public int size(){
            return values.size();
        }

    }

    @DisplayName("입출력으로 주어진 예시를 통과하는지 테스트 합니다.")
    @ParameterizedTest
    @ArgumentsSource(SolutionTestSuiteArgumentsProvider.class)
    void testExample(int[][] board, int[] moves, int expectAnswer) {
        NumberSet numberSet = NumberSet.from(board.length);

        int basket = 0;
        for(int num:moves){
            for(int i=0; i<board.length; i++){
                if (board[i][num-1] != 0){
                    numberSet.add(board[i][num-1]);

                    board[i][num-1] = 0;
                    basket++;
                    break;
                }
            }
        }

        int answer = basket - numberSet.size();

        assertThat(answer).isEqualTo(expectAnswer);
    }

    @Test
    void test(){
        //given
        List<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        NumberSet numberSet = new NumberSet(values);
        List<Integer> expect = List.of(1,2);

        //when
        numberSet.add(3);
        List<Integer> actual = numberSet.values;

        //then
        assertThat(actual).isEqualTo(expect);
    }
}
