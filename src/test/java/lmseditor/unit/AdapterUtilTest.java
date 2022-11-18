import lmseditor.backend.question.adapter.Util;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdapterUtilTest {

    private static final String BEGIN_MARK= "<!-- text begin -->";
    private static final String END_MARK= "<!-- text end -->";

    private static Stream<Arguments> provideStringsForParseText() {
        return Stream.of(
            Arguments.of("question name<br>text", "text"),
            Arguments.of(BEGIN_MARK + "text" + END_MARK, "text"),
            Arguments.of("question name<br>" + BEGIN_MARK + "text" + END_MARK, "text"),
            Arguments.of("very loooooooong question name<br>" + BEGIN_MARK + "text" + END_MARK, "text")
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForParseText")
    void parseTextTest(String given, String expected) {
        assertEquals(expected, Util.parseText(given));
    }

    private static Stream<Arguments> provideArgsForRemoveFirstLineShorterThan() {
        return Stream.of(
            Arguments.of("abc", 1, "abc"),
            Arguments.of("abc<br><br>xyz", 100, "<br>xyz"),
            Arguments.of("abc<br>xyz", 3, "abc<br>xyz"),
            Arguments.of("abc<br>xyz", 4, "xyz"),
            Arguments.of("<br>", 1, "")
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgsForRemoveFirstLineShorterThan")
    void removeFirstLineShorterThanTest(String givenStr, int givenMaxLength, String expected) {
        assertEquals(expected, Util.removeFirstLineShorterThan(givenStr, givenMaxLength));
    }

    private static Stream<Arguments> provideStringsForRemoveHtmlTags() {
        return Stream.of(
            Arguments.of("<br>", ""),
            Arguments.of("<br><br><br><br>", ""),
            Arguments.of("<div>question<div>", "question"),
            Arguments.of("<label><div>question<div><label>", "question"),
            Arguments.of("a<div>b<div>c", "abc")
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForRemoveHtmlTags")
    void removeHtmlTagsTest(String given, String expected) {
        assertEquals(expected, Util.removeHtmlTags(given));
    }
}
