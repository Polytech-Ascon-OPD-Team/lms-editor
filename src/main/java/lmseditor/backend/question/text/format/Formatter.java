package lmseditor.backend.question.text.format;

import lmseditor.backend.image.ImageBase64;

import java.util.List;

public abstract class Formatter {

    public abstract String getFormatOption();

    public abstract void setText(String text);
    public abstract void setImageList(List<ImageBase64> imageList);

    public abstract String getFormattedString();


}
