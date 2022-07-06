package test.programmers.test1;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class SolutionTestSuiteArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(
                        "1S2D*3T",
                        37
                ),
                Arguments.of(
                        "1D2S#10S",
                        9
                ),
                Arguments.of(
                        "1D2S0T",
                        3
                ),
                Arguments.of(
                        "1S*2T*3S",
                        23
                ),
                Arguments.of(
                        "1D#2S*3S",
                        5
                ),
                Arguments.of(
                        "1T2D3D#",
                        -4
                ),
                Arguments.of(
                        "1D2S3T*",
                        59
                )
        );
    }
}
