package test.programmers.test4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTestV2 {

    static class City{
        private final String value;

        public City(String value) {
            if (Objects.isNull(value) ){
                throw new RuntimeException("city가 null이 될 수 없음");
            }

            if (value.isEmpty() || value.isBlank() ){
                throw new RuntimeException("city가 빈 값이 될 수 없음");
            }

            this.value = value;
        }

        public static City ignoreCase(String value){
            value = Optional.ofNullable(value).map(v->v.toUpperCase()).orElse(null);

            return new City(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (getClass() != o.getClass()) return false;

            City city = (City) o;

            return Objects.equals(value, city.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }

    static class WorkTime{
        private final int value;
        private static final WorkTime ZERO = new WorkTime(0);
        private static final WorkTime HIT = new WorkTime(1);
        private static final WorkTime MISS = new WorkTime(5);

        public WorkTime(int value) {
            if (value < 0) {
                throw new RuntimeException("value는 0이상");
            }
            this.value = value;
        }

        public WorkTime sum(WorkTime workTime) {
            if (Objects.isNull(workTime)){
                throw new RuntimeException();
            }

            return new WorkTime(value + workTime.value);
        }

        public static WorkTime zero() {
            return ZERO;
        }

        public static WorkTime hit() {
            return HIT;
        }

        public static WorkTime miss() {
            return MISS;
        }

    }

    static class Cache{
        private final List<City> values;
        private final int size;

        public Cache(List<City> values, int size) {
            if(Objects.isNull(values)){
                throw new RuntimeException();
            }

            if(Objects.isNull(size)){
                throw new RuntimeException();
            }

            if (size < 0){
                throw new RuntimeException("size는 0이상");
            }

            for(int i=0; i<values.size(); i++){
                if (Objects.isNull(values.get(i))){
                    throw new RuntimeException("null 을 포함할 수 없음");
                }
            }

            this.values = values;
            this.size = size;
        }

        public static Cache from(int size){
            List<City> values = new ArrayList<>(Math.max(10,size));
            return new Cache(values,size);
        }

        public void access(City city, WorkTime workTime){
            if (Objects.isNull(city)){
                throw new RuntimeException();
            }

            if (values.contains(city)){
                hitRelocation(city);
                workTime.sum(WorkTime.hit());
                return;
            }

            missRelocation(city);
            workTime.sum(WorkTime.miss());
        }

        private void hitRelocation(City city){
            values.remove(city);
            values.add(0,city);
        }

        private void missRelocation(City city){
            if (values.size() == size){
                values.remove(values.size()-1);
            }
            values.add(0,city);
        }
    }

    @DisplayName("입출력으로 주어진 예시를 통과하는지 테스트 합니다.")
    @ParameterizedTest
    @ArgumentsSource(SolutionTestSuiteArgumentsProvider.class)
    void testExample(int cacheSize, String[] cities, int expectAnswer) {
        int answer = 0;


        assertThat(answer).isEqualTo(expectAnswer);
    }
}
