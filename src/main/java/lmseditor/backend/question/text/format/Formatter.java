package lmseditor.backend.question.text.format;

import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.image.ImageList;

public abstract class Formatter {

    public abstract String getFormatOption();

    public abstract void setText(String text);
    public abstract void setImageList(ImageList imageList);

    public abstract String getFormattedString();


}
