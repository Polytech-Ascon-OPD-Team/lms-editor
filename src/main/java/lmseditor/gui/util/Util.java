package lmseditor.gui.util;

import lmseditor.Main;
import lmseditor.backend.question.*;
import lmseditor.gui.panel.workspace.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Util {

    public static Question getQuestionForType(QuestionType type) {
        switch (type) {
            case CHOICE:
                return new QuestionChoice();
            case NUMERICAL:
                return new QuestionNumerical();
            case MATCHING:
                return new QuestionMatching();
            case SHORT_ANSWER:
                return new QuestionShortAnswer();
            default:
                return null;
        }
    }

    public static Workspace getWorkspaceForQuestionAndType(Question question, QuestionType type) {
        switch (type) {
            case CHOICE:
                return new QuestionChoiceAnswerWorkspace((QuestionChoice) question);
            case MATCHING:
                return new QuestionMatchingWorkspace((QuestionMatching) question);
            case NUMERICAL:
                return new QuestionNumericalAnswerWorkspace((QuestionNumerical) question);
            case SHORT_ANSWER:
                return new QuestionShortAnswerWorkspace((QuestionShortAnswer) question);
            default:
                return null;
        }
    }


    public static String chooseXMLPathFilePath() throws IOException {
        FileDialog chooser = new FileDialog(Main.mainFrame, "select XML file", FileDialog.LOAD);
        chooser.setAlwaysOnTop(true);
        chooser.setFile("*.xml");
        chooser.setVisible(true);
        if (chooser.getFile() == null) {
            throw new IOException("Wrong file!");
        }
        File result = new File(chooser.getDirectory() + chooser.getFile());
        return result.getPath();
    }

    public static String saveXMLPathFilePath() throws IOException {
        FileDialog chooser = new FileDialog(Main.mainFrame, "select XML file", FileDialog.SAVE);
        chooser.setAlwaysOnTop(true);
        chooser.setFile("*.xml");
        chooser.setVisible(true);
        if (chooser.getFile() == null) {
            throw new IOException("Wrong file!");
        }
        File result = new File(chooser.getDirectory() + chooser.getFile());
        return result.getPath();
    }
}
