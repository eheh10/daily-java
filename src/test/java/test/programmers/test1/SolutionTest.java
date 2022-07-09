package test.programmers.test1;

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
    void testExample(String dartResult, int expectAnswer) {
        int answer = 0;
        List<Integer> scores = new ArrayList<>();

        int computeScore = 0;
        StringBuilder setScore = new StringBuilder();

        for(int i=0; i<dartResult.length(); i++){
            char c = dartResult.charAt(i);

            if (!Character.isDigit(c)){
                continue;
            }

            setScore.append(c);

            if (Character.isDigit(dartResult.charAt(i+1))){
                setScore.append(dartResult.charAt(i+1));
                i++;
            }

            int d = 1;
            for(int j=setScore.length()-1; j>=0; j--){
                computeScore += (setScore.charAt(j) - '0')*d;
                d*=10;
            }

            scores.add(computeScore);

            setScore.setLength(0);
            computeScore = 0;
        }

        int scoresIdx = -1;

        for(int i=0; i<dartResult.length(); i++){
            char c = dartResult.charAt(i);

            if(Character.isDigit(c)){
                scoresIdx++;
                if(Character.isDigit(dartResult.charAt(i+1))){
                    i++;
                }
                continue;
            }

            if (Character.isUpperCase(c)){
                int n = 1;

                if (c=='D'){
                    n = 2;
                }else if(c=='T'){
                    n = 3;
                }

                scores.set(scoresIdx, (int)Math.pow(scores.get(scoresIdx),n));
                continue;
            }

            if (c == '#'){
                scores.set(scoresIdx, scores.get(scoresIdx)*-1);
                continue;
            }

            if (scoresIdx > 0) {
                scores.set(scoresIdx-1, scores.get(scoresIdx-1)*2);
            }

            scores.set(scoresIdx, scores.get(scoresIdx)*2);
        }

        for(int score:scores){
            answer += score;
        }

        assertThat(answer).isEqualTo(expectAnswer);
    }
}
