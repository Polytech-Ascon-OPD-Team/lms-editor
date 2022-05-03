package lmseditor.backend.question.adapter;

import lmseditor.backend.question.component.QuestionName;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class QuestionNameAdapter extends XmlAdapter<QuestionName, QuestionName> {

    @Override
    public QuestionName marshal(QuestionName v) throws Exception {
        return v;
    }

    @Override
    public QuestionName unmarshal(QuestionName v) throws Exception {
        String fullName = v.getFullName();
        int whitespaceIndex = fullName.lastIndexOf(' ');
        if (whitespaceIndex == -1) {
            v.setId(fullName);
            v.setName("");
        }
        String id = fullName.substring(0, whitespaceIndex);
        String name = fullName.substring(whitespaceIndex + 1);
        v.setId(id);
        v.setName(name);
        return v;
    }

}
