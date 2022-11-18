package lmseditor.backend.question.text.format;

import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.question.component.QuestionName;
import lmseditor.backend.question.text.TextWithImages;

public class QuestionNameFormatter extends Formatter {

    private Formatter formatter;
    private QuestionName name;

    public QuestionNameFormatter(Formatter formatter, QuestionName name) {
        this.formatter = formatter;
        this.name = name;
    }

    @Override
    public String getFormatOption() {
        return formatter.getFormatOption();
    }

    @Override
    public String format(ImageBase64 imageBase64) {
        return formatter.format(imageBase64);
    }

    @Override
    public String format(TextWithImages textWithImages) {
        return name.getFullName() + "<br>" + formatter.format(textWithImages);
    }
}
