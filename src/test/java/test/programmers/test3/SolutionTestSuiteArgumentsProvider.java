package test.programmers.test3;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class SolutionTestSuiteArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(
                        1, 1, 5,
                        new String[]{"08:00", "08:01", "08:02", "08:03"},
                        "09:00"
                ),
                Arguments.of(
                        2, 10, 2,
                        new String[]{"09:10", "09:09", "08:00"},
                        "09:09"
                ),
                Arguments.of(
                        2, 1, 2,
                        new String[]{"09:00", "09:00", "09:00", "09:00"},
                        "08:59"
                ),
                Arguments.of(
                        1, 1, 5,
                        new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"},
                        "00:00"
                ),
                Arguments.of(
                        1, 1, 1,
                        new String[]{"23:59"},
                        "09:00"
                ),
                Arguments.of(
                        10, 60, 45,
                        new String[]{"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59",
                                "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"},
                        "18:00"
                )
        );
    }
}
