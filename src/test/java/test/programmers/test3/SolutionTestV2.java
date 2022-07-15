package test.programmers.test3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTestV2 {

    static class Times {
        private final Queue<LocalTime> values;

        public Times(Queue<LocalTime> values) {
            this.values = values;
        }

        public static Times createDefault(){
            Queue<LocalTime> values = new PriorityQueue<>();
            return new Times(values);
        }

        public void add(LocalTime value) {
            values.add(value);
        }

        public boolean isEmpty() {
            return values.isEmpty();
        }

        public LocalTime peek() {
            return values.peek();
        }

        public LocalTime poll() {
            return values.poll();
        }
    }

    static class TimeDistribution {
        private final int num;
        private final int interval;
        private final int maxSize;
        private final LocalTime startTime;

        TimeDistribution(int num, int interval, int maxSize, LocalTime startTime) {
            this.num = num;
            this.interval = interval;
            this.maxSize = maxSize;
            this.startTime = startTime;
        }

        public List<LocalTime>[] compute(Times times) {
            List<LocalTime>[] distribution = new List[num];
            int dIdx = 0;

            for(int order=0; order < num; order++) {
                List<LocalTime> currentValues = new ArrayList<>(Math.max(10,maxSize));
                LocalTime currentTime = startTime.plusMinutes(order*interval);
                currentValues.add(currentTime);

                while(!times.isEmpty() && currentValues.size() <= maxSize){
                    if (times.peek().isAfter(currentTime)){
                        break;
                    }
                    currentValues.add(times.poll());
                }

                distribution[dIdx++] = currentValues;
            }

            return distribution;
        }
    }

    static class TimeSearch{
        private final List<LocalTime> values;

        public TimeSearch(List<LocalTime> values) {
            this.values = values;
        }

        public LocalTime searchTobeInvolveTime(int maxSize) {
            if(values.size() == 0 || values.size() <= maxSize){
                return values.get(0);
            }

            return values.get(values.size()-1).minusMinutes(1);
        }
    }

    @DisplayName("입출력으로 주어진 예시를 통과하는지 테스트 합니다.")
    @ParameterizedTest
    @ArgumentsSource(SolutionTestSuiteArgumentsProvider.class)
    void testExample(int n, int t, int m, String[] timetable, String expectAnswer) {
        Times times = Times.createDefault();

        for(String time : timetable) {
            StringTokenizer stringTokenizer = new StringTokenizer(time,":");
            int hour = Integer.parseInt(stringTokenizer.nextToken());
            int minute = Integer.parseInt(stringTokenizer.nextToken());

            times.add(LocalTime.of(hour,minute));
        }

        LocalTime departureTime = LocalTime.of(9,0);
        TimeDistribution timeDistribution = new TimeDistribution(n,t,m,departureTime);

        List<LocalTime> lastBus = timeDistribution.compute(times)[n-1];

        TimeSearch timeSearch = new TimeSearch(lastBus);
        String answer = String.valueOf(timeSearch.searchTobeInvolveTime(m));

        assertThat(answer).isEqualTo(expectAnswer);
    }
}
