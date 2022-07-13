package test.programmers.test1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;
import java.util.function.IntSupplier;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTestV2 {
    public enum Bonus{
        SINGLE('S',1),
        DOUBLE('D',2),
        TRIPLE('T',3)
        ;

        private static final Map<Character,Bonus> bonusFinder = createBonusFinder();

        private static Map<Character, Bonus> createBonusFinder() {
            Map<Character,Bonus> bonusFinder = new HashMap<>();

            for(Bonus b:values()){
                bonusFinder.put(b.code,b);
            }

            return bonusFinder;
        }

        private final char code;
        private final int pow;

        Bonus(char code, int pow) {
            this.code = code;
            this.pow = pow;
        }

        public static boolean isInvalid(char c){
            return !bonusFinder.containsKey(c);
        }

        public static Bonus valueOf(char c){
            return bonusFinder.get(c);
        }

        public static Bonus parse(String expression){
            for(int i=0; i<expression.length(); i++){
                char c = expression.charAt(i);
                if(Character.isDigit(c)){
                    continue;
                }
                if(isInvalid(c)){
                    continue;
                }
                return valueOf(c);
            }
            return null;
        }

        public Score computeScore(Score score){
            int pow = this.pow;
            return score.pow(pow);
        }
    }
    
    enum Option{
        STAR('*',2),
        SHARP('#',-1)
        ;

        private static final Map<Character,Option> optionFinder = createOptionFinder();

        private static Map<Character, Option> createOptionFinder() {
            Map<Character,Option> optionFinder = new HashMap<>();

            for(Option o:values()){
                optionFinder.put(o.code,o);
            }

            return optionFinder;
        }

        private final char code;
        private final int multiply;

        Option(char code, int multiply) {
            this.code = code;
            this.multiply = multiply;
        }

        public static boolean isInvalid(char c){
            return !optionFinder.containsKey(c);
        }

        public static Option valueOf(char c){
            return optionFinder.get(c);
        }

        public static Option parse(String expression){
            for(int i=0; i<expression.length(); i++){
                char c = expression.charAt(i);
                if(Character.isDigit(c)){
                    continue;
                }
                if(isInvalid(c)){
                    continue;
                }
                return valueOf(c);
            }
            return null;
        }

        public Score[] compute(Score previousScore, Score currentScore) {
            if(this == SHARP) {
                return new Score[]{previousScore, currentScore.multiply(this.multiply)};
            }

            if(this == STAR) {
                Score score = null;
                if (previousScore!=null) {
                    score = previousScore.multiply(this.multiply);
                }
                return new Score[]{score, currentScore.multiply(this.multiply)};
            }

            return new Score[]{previousScore, currentScore};
        }
    }
    
    static class PointElement{
        private final Score score;
        private final Bonus bonus;
        private final Optional<Option> option;

        PointElement(Score score, Bonus bonus, Option option) {
            this.score = score;
            this.bonus = bonus;
            this.option = Optional.ofNullable(option);
        }
    }

    static class Point{
        private final List<PointElement> values;

        Point(List<PointElement> values) {
            this.values = values;
        }

        public static Point from(int size) {
            List<PointElement> values = new ArrayList<>(Math.max(size,10));

            return new Point(values);
        }

        public void add(PointElement pointElement){
            values.add(pointElement);
        }

        public int compute() {
            int result = 0;
            List<Score> setScores = new ArrayList<>();

            for(int i=0; i < values.size(); i++) {
                PointElement pointElement = values.get(i);

                Score score = pointElement.score;
                Bonus bonus = pointElement.bonus;
                Optional<Option> option = pointElement.option;

                Score bonusScore = bonus.computeScore(score);

                if(option.isEmpty()){
                    setScores.add(new Score(bonusScore.value));
                    continue;
                }

                Score previousScore = null;
                if (i>0){
                    previousScore = setScores.get(i-1);
                }

                Score[] scores = option.get().compute(previousScore,bonusScore);

                if(scores[0]!=null) {
                    setScores.set(i-1,scores[0]);
                }
                setScores.add(scores[1]);
            }

            for(Score s:setScores){
                result += s.value;
            }

            return result;
        }
    }
    
    static class Score{
        private final int value;

        public Score(int value) {
            this.value = value;
        }

        public static Score parse(String expression){
            StringBuilder number = new StringBuilder(expression.length());

            for(int i=0; i<expression.length(); i++){
                if(!Character.isDigit(expression.charAt(i))){
                    break;
                }
                number.append(expression.charAt(i));
            }

            if(number.length()==0){
                return new Score(0);
            }

            return new Score(Integer.parseInt(number.toString()));
        }

        public static Score zero() {
            return new Score(0);
        }

        public Score sum(Score score){
            return new Score(value+score.value);
        }

        public Score pow(int pow) {
            return new Score((int)Math.pow(value,pow));
        }

        public Score multiply(int multiply) {
            return new Score(this.value*multiply);
        }
    }

    @DisplayName("입출력으로 주어진 예시를 통과하는지 테스트 합니다.")
    @ParameterizedTest
    @ArgumentsSource(SolutionTestSuiteArgumentsProvider.class)
    void testExample(String dartResult, int expectAnswer) {
        int answer = 0;

        List<String> expressions = new ArrayList<>();
        StringBuilder expression = new StringBuilder();

        for(int i=0; i < dartResult.length(); i++){
            char c = dartResult.charAt(i);

            if(Character.isDigit(c) && expression.length() > 1){
                expressions.add(expression.toString());
                expression.setLength(0);
            }

            expression.append(c);

            if(i==dartResult.length()-1){
                expressions.add(expression.toString());
            }
        }

        Point point = Point.from(3);
        for(String e : expressions){
            Score score = Score.parse(e);
            Bonus bonus = Bonus.parse(e);
            Option option = Option.parse(e);

            PointElement pointElement = new PointElement(score,bonus,option);
            point.add(pointElement);
        }

        answer = point.compute();

        assertThat(answer).isEqualTo(expectAnswer);
    }

    @ParameterizedTest
    @CsvSource({
            "10S*, 10",
            "0D, 0",
            "111T#, 111",
            "123456, 123456",
            "S, 0"
    })

    void test(String expression, int number){

//        assertThat(measure(()->parseNumber(expression))).isEqualTo(number);
//
//        assertThat(measure(()->parseNumber2(expression))).isEqualTo(number);

        Score score = Score.parse(expression);
        assertThat(score.value).isEqualTo(number);
    }

    @ParameterizedTest
    @CsvSource({
            "10D*, 2",
            "0T, 3",
            "5S#, 1"
    })
    void test2(String expression, int number){
        int actual = Bonus.parse(expression).pow;

        assertThat(actual).isEqualTo(number);
    }

    @ParameterizedTest
    @CsvSource({
            "10D*, 100",
            "0T, 0",
            "5S#, 5",
            "3T, 27"
    })
    void test3(String expression, int number){
        Score score = Score.parse(expression);
        Bonus bonus = Bonus.parse(expression);
        int actual = bonus.computeScore(score).value;

        assertThat(actual).isEqualTo(number);
    }

    @ParameterizedTest
    @CsvSource({
            "10D*, *",
            "0T, 0",
            "5S#, #"
    })
    void testOption(String expression, char expect) {
        Optional<Option> option = Optional.ofNullable(Option.parse(expression));

        if (option.isPresent()) {
            char actual = option.get().code;
            assertThat(actual).isEqualTo(expect);
        }else{
            assertThat(option).isEqualTo(Optional.empty());
        }
    }


    @ParameterizedTest
    @CsvSource({
            "10D*, 200",
            "0T, 0",
            "5S#, -5"
    })
    void testOptionCompute(String expression, int expect) {
        Score score = Score.parse(expression);
        Bonus bonus = Bonus.parse(expression);
        Score bonusScore = new Score(bonus.computeScore(score).value);

        Optional<Option> option = Optional.ofNullable(Option.parse(expression));

        if (option.isPresent()) {
            Score[] op = option.get().compute(null,bonusScore);
            int actual = op[1].value;
            assertThat(actual).isEqualTo(expect);
        }else{
            assertThat(bonusScore.value).isEqualTo(expect);
        }
    }

    private static int measure(IntSupplier supplier){
        long startTime = System.nanoTime();
        int result = supplier.getAsInt();
        long endTime = System.nanoTime() - startTime;
        System.out.println(endTime);
        return result;
    }

    private int parseNumber2(String expression) {
        StringBuilder number = new StringBuilder(expression.length());

        for(int i=0; i<expression.length(); i++){
            if(!Character.isDigit(expression.charAt(i))){
                break;
            }
            number.append(expression.charAt(i));
        }

        if(number.length()==0){
            return 0;
        }

        return Integer.parseInt(number.toString());
    }

    private static int parseNumber(String expression){
        int number = 0;
        int digit = 1;
        for(int i=expression.length()-1; i>=0; i--){
            if (!Character.isDigit(expression.charAt(i))){
                continue;
            }

            number += (expression.charAt(i)-'0')*digit;

            digit*=10;
        }

        return number;
    }
}
