package test.programmers.test2;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class SolutionTestSuiteArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {0, 0, 0, 0, 0},
                                {0, 0, 1, 0, 3},
                                {0, 2, 5, 0, 1},
                                {4, 2, 4, 4, 2},
                                {3, 5, 1, 3, 1}
                        },
                        new int[] {1, 5, 3, 5, 1, 2, 1, 4},
                        4
                ),
                Arguments.of(
                        new int[][]{
                                {0, 0, 0, 0, 0},
                                {0, 0, 1, 0, 3},
                                {0, 2, 5, 0, 1},
                                {4, 2, 4, 4, 2},
                                {3, 5, 1, 3, 1}
                        },
                        new int[] {1, 5, 3, 5, 1, 2, 5, 1, 4, 3},
                        8
                )
        );
    }
}
