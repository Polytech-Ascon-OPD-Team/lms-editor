package lmseditor.gui.util;

import lmseditor.backend.question.*;
import lmseditor.gui.panel.workspace.EmptyWorkspace;
import lmseditor.gui.panel.workspace.QuestionNumericalAnswerWorkspace;
import lmseditor.gui.panel.workspace.QuestionShortAnswerWorkspace;
import lmseditor.gui.panel.workspace.Workspace;

public class Util {

    public static Question getQuestionForType(QuestionType type) {
        switch (type) {
            case CHOICE: return new QuestionChoice();
            case NUMERICAL: return new QuestionNumerical();
            case MATCHING: return new QuestionMatching();
            case SHORT_ANSWER: return new QuestionShortAnswer();
            default: return null;
        }
    }

    public static Workspace getWorkspaceForQuestionAndType(Question question, QuestionType type) {
        switch (type) {
            case CHOICE: return new EmptyWorkspace(); // TODO
            case MATCHING: return new EmptyWorkspace(); // TODO
            case NUMERICAL: return new QuestionNumericalAnswerWorkspace((QuestionNumerical) question);
            case SHORT_ANSWER: return new QuestionShortAnswerWorkspace((QuestionShortAnswer) question);
            default: return null;
        }
    }

}
