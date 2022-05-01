package lmseditor.backend.question.text;

public class Text {

    private String text;

    public Text() {
        this.text = "";
    }

    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
