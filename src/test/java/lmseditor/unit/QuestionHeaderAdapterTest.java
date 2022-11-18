import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.question.adapter.QuestionHeaderAdapter;
import lmseditor.backend.question.component.QuestionHeader;
import lmseditor.backend.question.text.format.HtmlFormatter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static builder.QuestionHeaderBuilder.buildQuestionHeader;


public class QuestionHeaderAdapterTest {

    private static QuestionHeaderAdapter adapter;

    @BeforeAll
    static void setup() {
        adapter = new QuestionHeaderAdapter();
    }

    @Test
    @SneakyThrows
    void marshalTest() {
        QuestionHeader given = buildQuestionHeader(
            "category-1",
            "name",
            "Question answer 1 text",
            Arrays.asList(
                new ImageBase64("img-1.png", "/", "base64code-1"),
                new ImageBase64("img-2.png", "/", "base64code-2")
            ),
            new HtmlFormatter()
        );

        String expected = "category-1 ~ name<br>" +
            "<!-- text begin -->Question answer 1 text<!-- text end -->" +
            "<p><img src=\"@@PLUGINFILE@@/img-1.png\" ></p>" +
            "<p><img src=\"@@PLUGINFILE@@/img-2.png\" ></p>";

        assertEquals(expected, adapter.marshal(given).getTextWithImages().getXmlText());
    }

    @Test
    @SneakyThrows
    void unmarshalTest() {
        String givenXmlText = "category-1 ~ name<br>" +
            "<!-- text begin -->Question answer 1 text<!-- text end -->" +
            "<p><img src=\"@@PLUGINFILE@@/img-1.png\" ></p>" +
            "<p><img src=\"@@PLUGINFILE@@/img-2.png\" ></p>";
        QuestionHeader given = new QuestionHeader();
        Field xmlTextField = given.getTextWithImages().getClass().getDeclaredField("xmlText");
        xmlTextField.setAccessible(true);
        xmlTextField.set(given.getTextWithImages(), givenXmlText);

        QuestionHeader actual = adapter.unmarshal(given);

        assertEquals("Question answer 1 text", actual.getTextWithImages().getText());
    }
}
