package lmseditor.backend.question.text.format;

import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.question.text.TextWithImages;

public class HtmlFormatter extends Formatter {

    @Override
    public String getFormatOption() {
        return "html";
    }

    @Override
    public String format(ImageBase64 image) {
        StringBuilder imageCode = new StringBuilder("<img src=\"@@PLUGINFILE@@");
        imageCode.append(image.getPath());
        imageCode.append(image.getName());
        imageCode.append("\" ");
        imageCode.append(">");
        return imageCode.toString();
    }

    @Override
    public String format(TextWithImages textWithImages) {
        StringBuilder formattedString = new StringBuilder();
        formattedString.append("<!-- text begin -->");
        formattedString.append(textWithImages.getText());
        formattedString.append("<!-- text end -->");
        for (ImageBase64 image : textWithImages.getImageList()) {
            formattedString.append("<p>" + this.format(image) + "</p>");
        }
        return formattedString.toString();
    }

}
