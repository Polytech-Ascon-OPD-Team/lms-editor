package lmseditor.backend.question.component.format;

import lmseditor.backend.image.ImageBase64;

public abstract class Formatter {

    public abstract String getFormatOption();

    public abstract void setText(String text);
    public abstract void addImage(ImageBase64 image);
    public abstract void removeImageCode(int index);

    public abstract String getFormattedString();


}
