package lmseditor.backend.question.text;

public class Util {

    public static String firstSymbolToUpperCase(String string) {
        if (string.length() == 0) {
            return "";
        }
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public static String formatAnswerText(String text) {
        StringBuilder stringBuilder = new StringBuilder(firstSymbolToUpperCase(text));
        while (stringBuilder.length() > 0) {
            int lastIndex = stringBuilder.length() - 1;
            char sym = stringBuilder.charAt(lastIndex);
            if (isPunctuationMark(sym)) {
                stringBuilder.deleteCharAt(lastIndex);
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }

    public static boolean isPunctuationMark(char sym) {
        return (sym == '.' || sym == ',' || sym == ':' || sym == ';' || sym == '!' || sym == '?');
    }

}
