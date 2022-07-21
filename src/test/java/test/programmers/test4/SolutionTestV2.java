package test.programmers.test4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTestV2 {

    static class City{
        private final String value;

        public City(String value) {
            this.value = value.toUpperCase();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            City city = (City) o;

            return Objects.equals(value, city.value);
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }

    static class Cache{
        private final List<City> values;
        private final int size;
        private int workTime = 0;

        public Cache(List<City> values, int size) {
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

        public boolean contains(City city){
            return values.contains(city);
        }

        public void workAsMuch(int workTime){
            this.workTime += workTime;
        }

        public void removeLSU(){
            values.remove(values.size()-1);
        }

        public void load(City city) {
            values.add(0,city);
        }

        public void remove(City city) {
            values.remove(city);
        }
    }

    static class CacheFactory{
        private final Cache cache;

        public CacheFactory(Cache cache) {
            this.cache = cache;
        }

        public Handling of(City city){
            if (cache.contains(city)){
                return new Hit(cache);
            }

            return new Miss(cache);
        }
    }

    interface Handling{
        int WORKTIME = 1;
        void execute(City city);
    }

    static class Hit implements Handling{
        private final Cache cache;

        public Hit(Cache cache) {
            this.cache = cache;
        }

        public void execute(City city){
            cache.remove(city);
            cache.load(city);
            cache.workAsMuch(WORKTIME);
        }

    }

    static class Miss implements Handling{
        private final Cache cache;
        private static final int WORKTIME = 5;

        public Miss(Cache cache) {
            this.cache = cache;
        }

        public void execute(City city){
            if (cache.isFull()){
                cache.removeLSU();
            }
            cache.load(city);
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
