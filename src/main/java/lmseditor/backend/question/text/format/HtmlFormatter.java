package lmseditor.backend.question.text.format;

import lmseditor.backend.image.ImageBase64;

import java.util.ArrayList;
import java.util.List;

public class HtmlFormatter extends Formatter {

    private String text;
    private List<ImageBase64> images;

    public HtmlFormatter() {
        text = "";
        images = new ArrayList<ImageBase64>();
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
    public void setImageList(List<ImageBase64> imageList) {
        this.images = imageList;
    }

    @Override
    public String getFormattedString() {
        StringBuilder formattedString = new StringBuilder();
        formattedString.append("<!-- text begin -->");
        formattedString.append(text);
        formattedString.append("<!-- text end -->");
        for (ImageBase64 image : images) {
            formattedString.append("<p>" + this.getImageCode(image) + "</p>");
        }
        return formattedString.toString();
    }

    private String getImageCode(ImageBase64 image) {
        StringBuilder imageCode = new StringBuilder("<img src=\"@@PLUGINFILE@@");
        imageCode.append(image.getPath());
        imageCode.append(image.getName());
        imageCode.append("\" ");
        imageCode.append("height=\"" + image.getHeight() + "\" ");
        imageCode.append("width=\"" + image.getWidth() + "\" ");
        imageCode.append(">");
        return imageCode.toString();
    }

}
