package lmseditor.backend.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImageList {

    @XmlElement(name = "file")
    private List<ImageBase64> images;

    public ImageList() {
        this.images = new ArrayList<>();
    }

    public List<ImageBase64> getImages() {
        return images;
    }

}
