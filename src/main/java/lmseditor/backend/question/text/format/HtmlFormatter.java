package lmseditor.backend.question.text.format;

import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.image.ImageList;

public class HtmlFormatter extends Formatter {

    private String text;
    private ImageList imageList;

    public HtmlFormatter() {
        text = "";
        imageList = new ImageList();
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
    public void setImageList(ImageList imageList) {
        this.imageList = imageList;
    }

    @Override
    public String getFormattedString() {
        StringBuilder formattedString = new StringBuilder(text);
        for (ImageBase64 image : imageList.getImages()) {
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
