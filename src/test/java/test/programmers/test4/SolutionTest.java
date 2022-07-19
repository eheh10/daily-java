package test.programmers.test4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @DisplayName("입출력으로 주어진 예시를 통과하는지 테스트 합니다.")
    @ParameterizedTest
    @ArgumentsSource(SolutionTestSuiteArgumentsProvider.class)
    void testExample(int cacheSize, String[] cities, int expectAnswer) {
        int answer = 0;
        int hit = 1;
        int miss = 5;

        List<String> cache = new LinkedList<>();
        for(String city : cities){
            if(cacheSize == 0){
                answer+=miss;

                continue;
            }

            city = city.toUpperCase();
            int hitIdx = cache.indexOf(city);

            if(hitIdx > -1){
                answer+=hit;

                cache.add(city);
                cache.remove(hitIdx);

                continue;
            }

            answer+=miss;
            cache.add(city);

            if(cache.size()-1 < cacheSize){
                continue;
            }

            cache.remove(0);
        }

        assertThat(answer).isEqualTo(expectAnswer);
    }
}
