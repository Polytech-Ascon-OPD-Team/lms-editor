package lmseditor.backend.question.component.format;

import lmseditor.backend.image.ImageBase64;

import java.util.ArrayList;
import java.util.List;

public class HtmlFormatter extends Formatter {

    private String text;
    private List<String> imageCodes;

    public HtmlFormatter() {
        text = "";
        imageCodes = new ArrayList<>();
    }

    @Override
    public String getFormatOption() {
        return "html";
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void addImage(ImageBase64 image) {
        imageCodes.add("<p>" + this.getImageCode(image) + "</p>");
    }

    @Override
    public void removeImageCode(int index) {
        imageCodes.remove(index);
    }

    @Override
    public String getFormattedString() {
        StringBuilder formattedString = new StringBuilder(text);
        for (String imageCode : imageCodes) {
            formattedString.append(imageCode);
        }
        return formattedString.toString();
    }

    private String getImageCode(ImageBase64 image) {
        StringBuilder imageCode = new StringBuilder("<img src=\"@@PLUGINFILE@@");
        imageCode.append(image.getPath());
        imageCode.append(image.getName());
        imageCode.append(" ");
        imageCode.append("height=\"" + image.getHeight() + "\" ");
        imageCode.append("width=\"" + image.getWidth() + "\" ");
        imageCode.append(">");
        return imageCode.toString();
    }

}
