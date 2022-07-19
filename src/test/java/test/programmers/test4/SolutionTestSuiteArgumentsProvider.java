package test.programmers.test4;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class SolutionTestSuiteArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(
                        3,
                        new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"},
                        50
                ),
                Arguments.of(
                        3,
                        new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"},
                        21
                ),
                Arguments.of(
                        2,
                        new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"},
                        60
                ),
                Arguments.of(
                        5,
                        new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"},
                        52
                ),
                Arguments.of(
                        2,
                        new String[]{"Jeju", "Pangyo", "NewYork", "newyork"},
                        16
                ),
                Arguments.of(
                        0,
                        new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"},
                        25
                ),
                Arguments.of(
                        3,
                        new String[]{"A", "B", "A"},
                        11
                ),
                Arguments.of(
                        10,
                        new String[]{"1", "2", "3", "2"},
                        16
                ),
                Arguments.of(
                        0,
                        new String[]{"A", "B", "A", "A", "A"},
                        25
                )
        );
    }
}
