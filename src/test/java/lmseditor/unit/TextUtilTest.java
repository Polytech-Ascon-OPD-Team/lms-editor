import lmseditor.backend.question.text.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextUtilTest {

    @Test
    void firstSymbolToUpperCaseTest() {
        String given = "question name";
        String expected = "Question name";
        assertEquals(expected, Util.firstSymbolToUpperCase(given));
    }

    private static Stream<Arguments> provideCharsForIsPunctuationMark() {
        return Stream.of(
            Arguments.of('.', true),
            Arguments.of(',', true),
            Arguments.of(':', true),
            Arguments.of(';', true),
            Arguments.of('!', true),
            Arguments.of('?', true),
            Arguments.of('a', false),
            Arguments.of('1', false),
            Arguments.of('@', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCharsForIsPunctuationMark")
    void isPunctuationMarkTest(char given, boolean expected) {
        assertEquals(expected, Util.isPunctuationMark(given));
    }

    private static Stream<Arguments> provideStringsForFormatAnswerText() {
        return Stream.of(
            Arguments.of("question name.", "Question name"),
            Arguments.of("Question name...", "Question name"),
            Arguments.of("question name...", "Question name"),
            Arguments.of("Question name.,:;!?", "Question name"),
            Arguments.of(".a,a:a", ".a,a:a"),
            Arguments.of("...question name", "...question name")
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForFormatAnswerText")
    void formatAnswerTextTest(String given, String expected) {
        assertEquals(expected, Util.formatAnswerText(given));
    }
}
