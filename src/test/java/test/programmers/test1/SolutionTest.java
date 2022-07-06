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
        for(int i=0; i<dartResult.length(); i++){
            char c = dartResult.charAt(i);

            if (Character.isDigit(c)){
                scores.add(computeScore);

                StringBuilder setScore = new StringBuilder();

                setScore.append(c);

                if (Character.isDigit(dartResult.charAt(i+1))){
                    setScore.append(dartResult.charAt(i+1));
                    i++;
                }

                computeScore = Integer.parseInt(setScore.toString());

                continue;
            }

            if (Character.isUpperCase(c)){
                int n = c=='D'? 2: c=='T'? 3: 1;

                computeScore = (int)Math.pow(computeScore,n);
                continue;
            }

            if (c == '#'){
                computeScore *= -1;
                continue;
            }

            if (scores.size() > 1) {
                scores.set(scores.size() - 1, scores.get(scores.size() - 1) * 2);
            }

            computeScore *= 2;

        }

        scores.add(computeScore);

        for(int score:scores){
            answer += score;
        }

        assertThat(answer).isEqualTo(expectAnswer);
    }
}
