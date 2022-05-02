package lmseditor.backend.question.text;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TextWithImagesAdapter extends XmlAdapter<TextWithImages, TextWithImages> {

    @Override
    public TextWithImages marshal(TextWithImages v) throws Exception {
        v.generateFormattedText();
        return v;
    }

    @Override
    public TextWithImages unmarshal(TextWithImages v) throws Exception {
        String xmlText = v.getXmlText();
        if (xmlText.contains("<!-- text begin -->")) {
            // Если файл создавался с помощью этой программы, то можем точно определить текст вопроса
            int indexBegin = xmlText.indexOf("<!-- text begin -->") + 19;
            int indexEnd = xmlText.indexOf("<!-- text end -->");
            String text = xmlText.substring(indexBegin, indexEnd);
            v.getText().setText(text);
        } else {
            // Если начало похоже на имя вопроса (длина первой строки меньше 25 символов), то удаляем ее
            // Далее убираем все html тэги
            String text = removeFirstLineShorterThan(xmlText, 25);
            text = removeHtmlTags(text);
            v.getText().setText(text);
        }
        return v;
    }

    private String removeFirstLineShorterThan(String str, int maxLength) {
        int brIndex = str.indexOf("<br>");
        if (brIndex < maxLength) {
            str =  str.substring(brIndex + 4);
        }
        return str;
    }

    private String removeHtmlTags(String input) {
        StringBuilder stringBuilder = new StringBuilder(input);
        int indexBegin = input.indexOf('<');
        int indexEnd = input.indexOf('>');
        while((indexBegin >= 0) && (indexEnd >= 0)) {
            stringBuilder.replace(indexBegin, indexEnd, "");
            indexBegin = input.indexOf('<');
            indexEnd = input.indexOf('>');
        }
        return stringBuilder.toString();
    }

}
