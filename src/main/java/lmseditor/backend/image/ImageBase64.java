package lmseditor.backend.image;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImageBase64 {

    @XmlAttribute(name = "encoding")
    private static final String ENCODING = "base64";

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "path")
    private String path;

    @XmlValue
    private String base64;

    public ImageBase64() {
        this.name = "";
        this.path = "";
        this.base64 = "";
    }

    public ImageBase64(String name, String path, String base64) {
        this.name = name;
        this.path = path;
        this.base64 = base64;
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

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public static String encodeFileToBase64(File file) throws IOException {
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static BufferedImage decodeBase64ToImage(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        BufferedImage image = null;
        try {
            image = ImageIO.read(bin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public String toString() {
        return "Image {" +
                "name=" + name +
                ", path=" + path +
                ", base64=" + base64 +
                '}';
    }
}
