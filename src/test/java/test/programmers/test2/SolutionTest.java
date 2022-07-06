package test.programmers.test2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {
    @DisplayName("입출력으로 주어진 예시를 통과하는지 테스트 합니다.")
    @ParameterizedTest
    @ArgumentsSource(SolutionTestSuiteArgumentsProvider.class)
    void testExample(int[][] board, int[] moves, int expectAnswer) {
        int answer = 0;
        List<Integer> basket = new ArrayList<>(Math.max(10,board.length));

        for(int num:moves){
            for(int i=0; i<board.length; i++){
                if (board[i][num-1] != 0){
                    basket.add(board[i][num-1]);
                    board[i][num-1] = 0;
                    break;
                }
            }
        }

        for(int i=basket.size()-1; i > 0; i--){
            if (basket.size() < 2){
                break;
            }

            if (basket.get(i)==basket.get(i-1)){
                basket.remove(i);
                basket.remove(i-1);

                answer += 2;
            }
        }

        assertThat(answer).isEqualTo(expectAnswer);
    }
}
