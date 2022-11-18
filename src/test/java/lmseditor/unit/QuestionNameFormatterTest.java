import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.question.component.QuestionName;
import lmseditor.backend.question.text.TextWithImages;
import lmseditor.backend.question.text.format.Formatter;
import lmseditor.backend.question.text.format.HtmlFormatter;
import lmseditor.backend.question.text.format.QuestionNameFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class QuestionNameFormatterTest {

    private static QuestionName questionName;
    private static Formatter basicFormatter;
    private static Formatter formatter;

    @BeforeAll
    public static void setup() {
        questionName = new QuestionName("id", "name");
        basicFormatter = new HtmlFormatter();
        formatter = new QuestionNameFormatter(basicFormatter, questionName);
    }

    @Test
    public void formatOption() {
        Assertions.assertEquals(basicFormatter.getFormatOption(), formatter.getFormatOption());
    }

    @Test
    public void formatImageBase64() {
        ImageBase64 imageBase64 = new ImageBase64("name", "/path/", "base64");
        Assertions.assertEquals(basicFormatter.format(imageBase64), formatter.format(imageBase64));
    }

    @Test
    public void formatTextWithImages() {
        TextWithImages textWithImages = new TextWithImages();
        textWithImages.setText("text");

        String expected = questionName.getFullName() + "<br>" + basicFormatter.format(textWithImages);
        Assertions.assertEquals(expected, formatter.format(textWithImages));
    }

}
