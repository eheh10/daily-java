package test.programmers.test3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {
    static class Bus{
        private final LocalTime busTime;
        private final List<LocalTime> ride;

        public Bus(LocalTime busTime, List<LocalTime> ride) {
            this.busTime = busTime;
            this.ride = ride;
        }
    }

    @DisplayName("입출력으로 주어진 예시를 통과하는지 테스트 합니다.")
    @ParameterizedTest
    @ArgumentsSource(SolutionTestSuiteArgumentsProvider.class)
    void testExample(int n, int t, int m, String[] timetable, String expectAnswer) {
        String answer = "";
        LocalTime departureTime = LocalTime.of(9,0);
        Queue<LocalTime> crews = new PriorityQueue<>();

        for(String time:timetable){
            StringTokenizer stringTokenizer = new StringTokenizer(time,":");
            int hour = Integer.parseInt(stringTokenizer.nextToken());
            int minute = Integer.parseInt(stringTokenizer.nextToken());

            crews.add(LocalTime.of(hour,minute));
        }

//        while(!crews.isEmpty()){
//            System.out.println(crews.poll());
//        }

        List<Bus> buses = new ArrayList<>();

        for(int next=0,order=0; order < n; next+=t,order++){
            LocalTime busTime = departureTime.plusMinutes(next);
            List<LocalTime> ride = new ArrayList<>();

            while(!crews.isEmpty() && ride.size() < m){
                if (crews.peek().isAfter(busTime)){
                    break;
                }
                ride.add(crews.poll());
            }

            buses.add(new Bus(busTime,ride));
        }

        Bus lastBus = buses.get(buses.size()-1);
        LocalTime lastTime = lastBus.busTime;
        List<LocalTime> lastRide = lastBus.ride;
        LocalTime con = null;

        if(lastRide.size()==0){
            con = lastTime;
        }else if(lastRide.size() < m){
            con = lastRide.get(lastRide.size()-1);
            if (con.isBefore(lastTime)){
                con = lastTime;
            }
        }else{
            con = lastRide.get(lastRide.size()-1).minusMinutes(1);
        }

        answer = String.valueOf(con);

        assertThat(answer).isEqualTo(expectAnswer);
    }
}
