package lmseditor.backend.question.adapter;

import lmseditor.backend.question.text.TextWithImages;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TextWithImagesAdapter extends XmlAdapter<TextWithImages, TextWithImages> {

    @Override
    public TextWithImages marshal(TextWithImages v) throws Exception {
        v.generateFormattedText();
        return v;
    }

    @Override
    public TextWithImages unmarshal(TextWithImages v) throws Exception {
        String text = Util.parseText(v.getXmlText());
        v.setText(text);
        return v;
    }

}
