package lmseditor.backend.question.adapter;

import lmseditor.backend.question.component.QuestionHeader;
import lmseditor.backend.question.text.format.Formatter;
import lmseditor.backend.question.text.format.QuestionNameFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class QuestionHeaderAdapter extends XmlAdapter<QuestionHeader, QuestionHeader> {

    @Override
    public QuestionHeader marshal(QuestionHeader v) throws Exception {
        Formatter oldFormatter = v.getTextWithImages().getFormatter();
        Formatter headerFormatter = new QuestionNameFormatter(oldFormatter, v.getName());
        v.getTextWithImages().setFormatter(headerFormatter);
        v.getTextWithImages().generateFormattedText();
        v.getTextWithImages().setFormatter(oldFormatter);
        return v;
    }

    @Override
    public QuestionHeader unmarshal(QuestionHeader v) throws Exception {
        TextWithImagesAdapter adapter = new TextWithImagesAdapter();
        adapter.unmarshal(v.getTextWithImages());
        return v;
    }

}