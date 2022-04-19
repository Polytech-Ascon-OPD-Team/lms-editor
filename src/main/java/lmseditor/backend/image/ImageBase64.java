package lmseditor.backend.image;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImageBase64 {

    @XmlAttribute(name = "encoding")
    private static final String ENCODING = "base64";

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "path")
    private String path;

    @XmlTransient
    private int width;

    @XmlTransient
    private int height;

    @XmlValue
    private String base64;

    public ImageBase64() {
    }

    public ImageBase64(String name, String path, int width, int height) {
        this.name = name;
        this.path = path;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

}
