package lmseditor.backend.question.text.format;

import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.question.text.TextWithImages;

public abstract class Formatter {
    public abstract String getFormatOption();
    public abstract String format(ImageBase64 imageBase64);
    public abstract String format(TextWithImages textWithImages);
}
