package builder;

import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.question.component.QuestionHeader;
import lmseditor.backend.question.component.QuestionName;
import lmseditor.backend.question.text.TextWithImages;
import lmseditor.backend.question.text.format.Formatter;

import java.util.List;

public class QuestionHeaderBuilder {

    public static QuestionHeader buildQuestionHeader(
        String questionNameId,
        String questionNameName,
        String textWithImagesText,
        List<ImageBase64> images,
        Formatter formatter
    ) {
        QuestionName questionName = new QuestionName(questionNameId, questionNameName);
        TextWithImages textWithImages = new TextWithImages();
        textWithImages.setText(textWithImagesText);
        textWithImages.setImageList(images);
        textWithImages.setFormatter(formatter);

        QuestionHeader questionHeader = new QuestionHeader(questionName);
        questionHeader.setTextWithImages(textWithImages);
        return questionHeader;
    }
}
