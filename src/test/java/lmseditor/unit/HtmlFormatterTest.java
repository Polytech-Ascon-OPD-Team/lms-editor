import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.question.text.TextWithImages;
import lmseditor.backend.question.text.format.Formatter;
import lmseditor.backend.question.text.format.HtmlFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HtmlFormatterTest {

    private static Formatter formatter;

    @BeforeAll
    public static void setup() {
        formatter = new HtmlFormatter();
    }

    @Test
    public void formatOption() {
        Assertions.assertEquals("html", formatter.getFormatOption());
    }

    @Test
    public void formatImageBase64() {
        ImageBase64 imageBase64 = new ImageBase64("name", "/path/", "base64");
        String expected = "<img src=\"@@PLUGINFILE@@/path/name\" >";
        Assertions.assertEquals(expected, formatter.format(imageBase64));
    }

    @Test
    public void formatTextWithImagesNoImages() {
        TextWithImages textWithImages = new TextWithImages();
        textWithImages.setText("text");

        String expected = "<!-- text begin -->text<!-- text end -->";
        Assertions.assertEquals(expected, formatter.format(textWithImages));
    }

    @Test
    public void formatTextWithImages() {
        TextWithImages textWithImages = new TextWithImages();
        textWithImages.setText("text");
        ImageBase64 imageBase64_1 = new ImageBase64("name_1", "/path_1/", "base64_1");
        ImageBase64 imageBase64_2 = new ImageBase64("name_2", "/path_2/", "base64_2");
        textWithImages.getImageList().add(imageBase64_1); textWithImages.getImageList().add(imageBase64_2);

        String expected = "<!-- text begin -->text<!-- text end -->" +
                "<p>" + formatter.format(imageBase64_1) + "</p>" +
                "<p>" + formatter.format(imageBase64_2) + "</p>";
        Assertions.assertEquals(expected, formatter.format(textWithImages));
    }

}
