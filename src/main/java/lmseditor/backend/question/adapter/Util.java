package lmseditor.backend.question.adapter;

public class Util {

    public static String parseText(String xmlText) {
        String text;
        if (xmlText.contains("<!-- text begin -->")) {
            // Если файл создавался с помощью этой программы, то можем точно определить текст вопроса
            int indexBegin = xmlText.indexOf("<!-- text begin -->") + 19;
            int indexEnd = xmlText.indexOf("<!-- text end -->");
            text = xmlText.substring(indexBegin, indexEnd);

        } else {
            // Если начало похоже на имя вопроса (длина первой строки меньше 25 символов), то удаляем ее
            // Далее убираем все html тэги
            text = Util.removeFirstLineShorterThan(xmlText, 25);
            text = Util.removeHtmlTags(text);
        }
        return text;
    }

    public static String removeFirstLineShorterThan(String str, int maxLength) {
        int brIndex = str.indexOf("<br>");
        if ((brIndex != -1) && (brIndex < maxLength)) {
            str =  str.substring(brIndex + 4);
        }
        return str;
    }

    public static String removeHtmlTags(String input) {
        StringBuilder stringBuilder = new StringBuilder(input);
        int indexBegin = stringBuilder.indexOf("<");
        int indexEnd = stringBuilder.indexOf(">");
        while((indexBegin >= 0) && (indexEnd >= 0)) {
            stringBuilder.replace(indexBegin, indexEnd + 1, "");
            indexBegin = stringBuilder.indexOf("<");
            indexEnd = stringBuilder.indexOf(">");
        }
        return stringBuilder.toString();
    }

}
