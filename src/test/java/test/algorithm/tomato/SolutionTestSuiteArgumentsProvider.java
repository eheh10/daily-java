package test.algorithm.tomato;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class SolutionTestSuiteArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(
                        6, 4,
                        new int[][]{
                                {0,0,0,0,0,0},
                                {0,0,0,0,0,0},
                                {0,0,0,0,0,0},
                                {0,0,0,0,0,1},
                        },
                        8
                ),
                Arguments.of(
                        6, 4,
                        new int[][]{
                                {0,-1,0,0,0,0},
                                {-1,0,0,0,0,0},
                                {0,0,0,0,0,0},
                                {0,0,0,0,0,1},
                        },
                        -1
                ),
                Arguments.of(
                        6, 4,
                        new int[][]{
                                {1,-1,0,0,0,0},
                                {0,-1,0,0,0,0},
                                {0,0,0,0,-1,0},
                                {0,0,0,0,-1,1},
                        },
                        6
                ),
                Arguments.of(
                        5, 5,
                        new int[][]{
                                {-1,1,0,0,0},
                                {0,-1,-1,-1,0},
                                {0,-1,-1,-1,0},
                                {0,-1,-1,-1,0},
                                {0,0,0,0,0},
                        },
                        14
                ),
                Arguments.of(
                        2, 2,
                        new int[][]{
                                {1,-1},
                                {-1,1},
                        },
                        0
                )
        );
    }
}
