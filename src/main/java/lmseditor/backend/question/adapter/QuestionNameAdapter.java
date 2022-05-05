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
        int sepIndex = fullName.lastIndexOf(" ~ ");
        if (sepIndex == -1) {
            v.setId(fullName);
            v.setName("");
            return v;
        }
        String id = fullName.substring(0, sepIndex);
        String name = fullName.substring(sepIndex + 3);
        v.setId(id);
        v.setName(name);
        return v;
    }

}
