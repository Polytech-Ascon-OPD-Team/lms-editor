package lmseditor.backend.question.adapter;

import lmseditor.backend.question.text.QuestionText;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class QuestionTextAdapter extends XmlAdapter<QuestionText, QuestionText> {

    @Override
    public QuestionText marshal(QuestionText v) throws Exception {
        v.generateFormattedText();
        return v;
    }

    @Override
    public QuestionText unmarshal(QuestionText v) throws Exception {
        String text = Util.parseText(v.getXmlText());
        v.setText(text);
        return v;
    }

}
