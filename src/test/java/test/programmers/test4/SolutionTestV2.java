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
        private WorkTime workTime = new WorkTime(0);

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

        public boolean isFull(){
            return values.size() == size;
        }

        public boolean isHit(City city){
            return values.contains(city);
        }

        public void workAsMuch(WorkTime workTime){
            this.workTime = this.workTime.sum(workTime);
        }

        public void removeLSU(){
            values.remove(values.size()-1);
        }

        public void write(City city) {
            if (Objects.isNull(city)){
                throw new RuntimeException();
            }
            values.add(0,city);
        }

        public void remove(City city) {
            if (Objects.isNull(city)){
                throw new RuntimeException();
            }
            values.remove(city);
        }
    }

    static class CacheFactory{
        private final Cache cache;

        public CacheFactory(Cache cache) {
            this.cache = cache;
        }

        public Handling of(City city){
            if (cache.isHit(city)){
                return new Hit(cache);
            }

            return new Miss(cache);
        }
    }

    interface Handling{
        WorkTime WORKTIME = new WorkTime(1);
        void execute(City city);
    }

    static class Hit implements Handling{
        private final Cache cache;

        public Hit(Cache cache) {
            this.cache = cache;
        }

        public void execute(City city){
            cache.remove(city);
            cache.write(city);
            cache.workAsMuch(WORKTIME);
        }

    }

    static class Miss implements Handling{
        private final Cache cache;
        private static final WorkTime WORKTIME = new WorkTime(5);

        public Miss(Cache cache) {
            this.cache = cache;
        }

        public void execute(City city){
            if (cache.isFull()){
                cache.removeLSU();
            }
            cache.write(city);
            cache.workAsMuch(WORKTIME);
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
